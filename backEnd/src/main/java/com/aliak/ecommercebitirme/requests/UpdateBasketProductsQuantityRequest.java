package com.aliak.ecommercebitirme.requests;

import lombok.Data;

@Data
public class UpdateBasketProductsQuantityRequest {
    Long BasketProductId;
    Long UserId;
    Long Quantity;
}
