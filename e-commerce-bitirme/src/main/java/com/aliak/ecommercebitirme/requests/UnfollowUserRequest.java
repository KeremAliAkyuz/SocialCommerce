package com.aliak.ecommercebitirme.requests;

import lombok.Data;

@Data
public class UnfollowUserRequest {
    Long FollowerId;
    Long FollowedId;
}
