package com.aliak.ecommercebitirme.requests;

import lombok.Data;

@Data
public class DeleteFavoriteProductRequest {
    Long FavoriteProductId;
    Long UserId;
}
