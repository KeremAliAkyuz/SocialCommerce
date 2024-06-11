package com.aliak.ecommercebitirme.service;

import com.aliak.ecommercebitirme.entity.BasketProduct;
import com.aliak.ecommercebitirme.entity.FavoriteProduct;
import com.aliak.ecommercebitirme.entity.Follower;
import com.aliak.ecommercebitirme.entity.User;
import com.aliak.ecommercebitirme.repository.FollowerRepository;
import com.aliak.ecommercebitirme.requests.DeleteBasketProductRequest;
import com.aliak.ecommercebitirme.requests.FollowUserRequest;
import com.aliak.ecommercebitirme.requests.UnfollowUserRequest;
import com.aliak.ecommercebitirme.responses.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final UserService userService;
    private final FavoriteProductService favoriteProductService;

    public Follower followUser(FollowUserRequest followUserRequest){
        User followedUser = userService.getOneUser(followUserRequest.getFollowedId());
        User followerUser = userService.getOneUser(followUserRequest.getFollowerId());

        Follower toSave = new Follower();
        toSave.setFollowerUser(followerUser);
        toSave.setFollowedUser(followedUser);
        toSave.setDateTime(LocalDateTime.now());
        if(!Objects.equals(followUserRequest.getFollowedId(), followUserRequest.getFollowerId()))
            followerRepository.save(toSave);
        return toSave;
    }
    public void unfollowUser(UnfollowUserRequest unfollowUserRequest) {
        User followerUser = userService.getOneUser(unfollowUserRequest.getFollowerId());
        List<Follower> followers = followerRepository.findByFollowerUser(followerUser);
        List<Follower> deletedFollowers = followers.stream().filter(f -> Objects.equals(f.getFollowedUser().getId(), unfollowUserRequest.getFollowedId())).toList();

        followerRepository.deleteAll(deletedFollowers);
    }

    public List<Follower> getAllFolloweds(Long id){
        User followerUser = userService.getOneUser(id);
        return followerRepository.findByFollowerUser(followerUser);
    }

    public List<Follower> getAllFollowers(Long id){
        User followedUser = userService.getOneUser(id);
        return followerRepository.findByFollowedUser(followedUser);
    }

    public List<User> getAllFollowedUsers(Long id){
        User followerUser = userService.getOneUser(id);
        List<Follower> followeds = followerRepository.findByFollowerUser(followerUser);
        return followeds.stream().map(Follower::getFollowedUser).toList();
    }

    public boolean isUserFollowed(Long followerUserId,Long followedUserId) {
        User followerUser = userService.getOneUser(followerUserId);
        User followedUser = userService.getOneUser(followedUserId);
        List<Follower> followeds = followerRepository.findByFollowerUser(followerUser);
        return followeds.stream().anyMatch(f -> Objects.equals(f.getFollowedUser(), followedUser));
    }

    public List<Follower> getAllFollowData(){
        return followerRepository.findAll();
    }

    public List<ActivityResponse> getActivityResponseById(Long id){
        List<Follower> followerData = getAllFollowerDataById(id);
        List<FavoriteProduct> favoriteProducts = favoriteProductService.findFavoriteProductActivitiesFromFollowerUsers(id);
        List<ActivityResponse> activityResponse = new java.util.ArrayList<>(followerData.stream()
                .map(follower -> new ActivityResponse(follower.getId(), follower.getFollowerUser(),
                        follower.getFollowedUser(), follower.getDateTime(), "follower"))
                .toList());
        activityResponse.addAll(favoriteProducts.stream()
                .map(favoriteProduct -> new ActivityResponse(favoriteProduct.getId(), favoriteProduct.getUser(),
                        favoriteProduct.getFavoriteProductId(), favoriteProduct.getDateTime(), "favoriteProduct"))
                .toList());
        return activityResponse.stream()
                .sorted(Comparator.comparing(ActivityResponse::getDateTime).reversed())
                .collect(Collectors.toList());
    }
    public List<Follower> getAllFollowerDataById(Long id){
        List<User> followedUsers = getAllFollowedUsers(id);
        List<Follower> followData = new java.util.ArrayList<>(List.of());
        for(User f : followedUsers){
            followData.addAll(followerRepository.findByFollowerUser(f));
        }
        return followData.stream()
                .sorted(Comparator.comparing(Follower::getDateTime))
                .collect(Collectors.toList());
    }
}
