package com.aliak.ecommercebitirme.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MostFavoritedProductResponse {
    private Long favoriteProductId;
    private Long count;
}
