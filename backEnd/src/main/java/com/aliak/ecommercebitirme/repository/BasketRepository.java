package com.aliak.ecommercebitirme.repository;

import com.aliak.ecommercebitirme.entity.BasketProduct;
import com.aliak.ecommercebitirme.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<BasketProduct,Long> {

    List<BasketProduct> findByUserId(Long userId);
    BasketProduct findByUserIdAndBasketProductId(Long userId,Long basketProductId);
}
