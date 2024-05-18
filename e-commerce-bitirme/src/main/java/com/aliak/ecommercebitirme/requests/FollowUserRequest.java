package com.aliak.ecommercebitirme.requests;

import lombok.Data;

@Data
public class FollowUserRequest {
    Long FollowerId;
    Long FollowedId;
}
