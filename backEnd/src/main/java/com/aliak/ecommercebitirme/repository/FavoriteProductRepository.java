package com.aliak.ecommercebitirme.repository;

import com.aliak.ecommercebitirme.entity.FavoriteProduct;
import com.aliak.ecommercebitirme.responses.MostFavoritedProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct,Long> {

    List<FavoriteProduct> findByUserId(Long userId);
    List<FavoriteProduct> findByFavoriteProductId(Long favoriteProductId);
    @Query("SELECT new com.aliak.ecommercebitirme.responses.MostFavoritedProductResponse(e.favoriteProductId, COUNT(e)) " +
            "FROM FavoriteProduct e GROUP BY e.favoriteProductId ORDER BY COUNT(e) DESC")
    List<MostFavoritedProductResponse> findFavoriteProductCounts();
}
