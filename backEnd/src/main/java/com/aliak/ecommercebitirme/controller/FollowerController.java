package com.aliak.ecommercebitirme.controller;

import com.aliak.ecommercebitirme.entity.BasketProduct;
import com.aliak.ecommercebitirme.entity.Follower;
import com.aliak.ecommercebitirme.requests.AddBasketProductRequest;
import com.aliak.ecommercebitirme.requests.DeleteBasketProductRequest;
import com.aliak.ecommercebitirme.requests.FollowUserRequest;
import com.aliak.ecommercebitirme.requests.UnfollowUserRequest;
import com.aliak.ecommercebitirme.responses.ActivityResponse;
import com.aliak.ecommercebitirme.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follower")
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;

    @PostMapping("/follow")
    public Follower addFollower(@RequestBody FollowUserRequest followUserRequest){
        return followerService.followUser(followUserRequest);
    }

    @RequestMapping(value = "/unfollow", method = { RequestMethod.POST })
    public String unfollowUser(@RequestBody UnfollowUserRequest unfollowUserRequest)
    {
        followerService.unfollowUser(unfollowUserRequest);
        return "Deleted Successfully.";
    }

    @GetMapping("/followerUser/{userId}")
    public List<Follower> getAllFolloweds(@PathVariable Long userId){
        return followerService.getAllFolloweds(userId);
    }

    @GetMapping("/followedUser/{userId}")
    public List<Follower> getAllFollowers(@PathVariable Long userId){
        return followerService.getAllFollowers(userId);
    }

    @GetMapping("isFollowed/{followerUserId}/{followedUserId}")
    public boolean isFollowed(@PathVariable Long followerUserId, @PathVariable Long followedUserId) {
        return followerService.isUserFollowed(followerUserId,followedUserId);
    }

    @GetMapping("getAll")
    public List<Follower> getAll(){
        return followerService.getAllFollowData();
    }

    @GetMapping("getFollowerActivity/{id}")
    public List<Follower> getFollowerActivity(@PathVariable Long id){
        return followerService.getAllFollowerDataById(id);
    }

    @GetMapping("getFollowerAndFavoriteProductActivity/{id}")
    public List<ActivityResponse> getFollowerAndFavoriteProductActivity(@PathVariable Long id){
        return followerService.getActivityResponseById(id);
    }
}
