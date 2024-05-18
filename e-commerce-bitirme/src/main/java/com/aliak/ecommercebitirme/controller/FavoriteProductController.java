package com.aliak.ecommercebitirme.controller;

import com.aliak.ecommercebitirme.entity.FavoriteProduct;
import com.aliak.ecommercebitirme.requests.AddFavoriteProductRequest;
import com.aliak.ecommercebitirme.requests.DeleteFavoriteProductRequest;
import com.aliak.ecommercebitirme.responses.MostFavoritedProductResponse;
import com.aliak.ecommercebitirme.service.FavoriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoriteProducts")
@RequiredArgsConstructor
public class FavoriteProductController {
    private final FavoriteProductService favoriteProductService;

    @PostMapping("/add")
    public FavoriteProduct addFavoriteProduct(@RequestBody AddFavoriteProductRequest addFavoriteProductRequest){
        return favoriteProductService.addFavoriteProduct(addFavoriteProductRequest);
    }

    @GetMapping("/{userId}")
    public List<FavoriteProduct> getAllFavoriteProductsById(@PathVariable Long userId){
        return favoriteProductService.getFavoriteProductsByUserId(userId);
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.POST })
    public String deleteFavoriteProduct(@RequestBody DeleteFavoriteProductRequest deleteFavoriteProductRequest)
    {
        favoriteProductService.deleteFavoriteProduct(deleteFavoriteProductRequest);
        return "Deleted Successfully.";
    }

    @GetMapping("/mostFavoritedProducts")
    public List<MostFavoritedProductResponse> getMostFavoritedProducts(){
        return favoriteProductService.mostFavoritedProducts();
    }

    @GetMapping("/totalFavoritesCountOfProduct/{productId}")
    public int getTotalFavoritesCount(@PathVariable Long productId){
        return favoriteProductService.totalFavoritesCount(productId);
    }

    @GetMapping("/getAll")
    public List<FavoriteProduct> getAll(){
        return favoriteProductService.getAllFavoriteProducts();
    }

    @GetMapping("/getAllFollowedsFavoriteProducts/{id}")
    public List<FavoriteProduct> getAllFollowedsFavoriteProducts(@PathVariable Long id){
        return favoriteProductService.findFavoriteProductActivitiesFromFollowerUsers(id);
    }
}
