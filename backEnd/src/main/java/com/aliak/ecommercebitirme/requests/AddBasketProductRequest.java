package com.aliak.ecommercebitirme.requests;

import lombok.Data;

@Data
public class AddBasketProductRequest {
    Long BasketProductId;
    Long UserId;
    Long Quantity;
}
