package com.aliak.ecommercebitirme.requests;

import lombok.Data;

@Data
public class AddFavoriteProductRequest {
    Long FavoriteProductId;
    Long UserId;
}
