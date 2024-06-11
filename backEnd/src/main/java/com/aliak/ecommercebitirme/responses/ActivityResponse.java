package com.aliak.ecommercebitirme.responses;

import com.aliak.ecommercebitirme.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityResponse {
    private Long id;
    private User followerUser;
    private User followedUser;
    private User user;
    private LocalDateTime dateTime;
    private String type;
    private Long favoriteProductId;


    // Favori ürünler için kullanılacak constructor
    public ActivityResponse(Long id,User followerUser,User followedUser, LocalDateTime dateTime, String type) {
        this.id = id;
        this.followerUser = followerUser;
        this.followedUser = followedUser;
        this.user = null;
        this.favoriteProductId = null;
        this.dateTime = dateTime;
        this.type = type;
    }

    // Takipçiler için kullanılacak constructor
    public ActivityResponse(Long id,User user,Long favoriteProductId, LocalDateTime dateTime, String type) {
        this.id = id;
        this.user = user;
        this.favoriteProductId = favoriteProductId;
        this.dateTime = dateTime;
        this.type = type;
        this.followerUser = null;
        this.followedUser = null;
    }
}
