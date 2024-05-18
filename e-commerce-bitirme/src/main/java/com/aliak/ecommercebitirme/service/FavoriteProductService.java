package com.aliak.ecommercebitirme.service;

import com.aliak.ecommercebitirme.entity.FavoriteProduct;
import com.aliak.ecommercebitirme.entity.Follower;
import com.aliak.ecommercebitirme.entity.User;
import com.aliak.ecommercebitirme.repository.FavoriteProductRepository;
import com.aliak.ecommercebitirme.repository.FollowerRepository;
import com.aliak.ecommercebitirme.requests.AddFavoriteProductRequest;
import com.aliak.ecommercebitirme.requests.DeleteFavoriteProductRequest;
import com.aliak.ecommercebitirme.responses.MostFavoritedProductResponse;
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
public class FavoriteProductService {
    private final FavoriteProductRepository favoriteProductRepository;
    private final UserService userService;
    private final FollowerRepository followerRepository;

    public List<FavoriteProduct> getAllFavoriteProducts() {
        return favoriteProductRepository.findAll();
    }

    public FavoriteProduct addFavoriteProduct(AddFavoriteProductRequest favoriteProduct)
    {
        User user = userService.getOneUser(favoriteProduct.getUserId());
        LocalDateTime now = LocalDateTime.now();
        FavoriteProduct toSave = new FavoriteProduct();
        toSave.setFavoriteProductId(favoriteProduct.getFavoriteProductId());
        toSave.setDateTime(now);
        toSave.setUser(user);

        return favoriteProductRepository.save(toSave);
    }
    public List<FavoriteProduct> getFavoriteProductsByUserId(Long userId){

        return favoriteProductRepository.findByUserId(userId);
    }
    public void deleteFavoriteProduct(DeleteFavoriteProductRequest deleteFavoriteProductRequest)
    {
        List<FavoriteProduct> favoriteProducts = favoriteProductRepository.findByUserId(deleteFavoriteProductRequest.getUserId());
        List<FavoriteProduct> deletedProducts = favoriteProducts.stream().filter(fp -> Objects.equals(fp.getFavoriteProductId(), deleteFavoriteProductRequest.getFavoriteProductId())).toList();

        favoriteProductRepository.deleteAll(deletedProducts);
    }

    public List<MostFavoritedProductResponse> mostFavoritedProducts(){
        return favoriteProductRepository.findFavoriteProductCounts();
    }

    public int totalFavoritesCount(Long productId) {
        return favoriteProductRepository.findByFavoriteProductId(productId).size();
    }

    public List<FavoriteProduct> findFavoriteProductActivitiesFromFollowerUsers(Long userId) {
        User followerUser = userService.getOneUser(userId);
        List<Follower> followeds = followerRepository.findByFollowerUser(followerUser);
        List<User> followedUsers = followeds.stream().map(Follower::getFollowedUser).toList();

        List<FavoriteProduct> favoriteProducts = new java.util.ArrayList<>(List.of());
        for(User f : followedUsers){
            favoriteProducts.addAll(favoriteProductRepository.findByUserId(f.getId()));
        }

        return favoriteProducts.stream()
                .sorted(Comparator.comparing(FavoriteProduct::getDateTime))
                .collect(Collectors.toList());
    }
}
