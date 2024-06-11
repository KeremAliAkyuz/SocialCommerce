package com.aliak.ecommercebitirme.requests;

import lombok.Data;

@Data
public class DeleteBasketProductRequest {
    Long BasketProductId;
    Long UserId;
}
