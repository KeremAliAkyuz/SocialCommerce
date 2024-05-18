package com.aliak.ecommercebitirme.controller;

import com.aliak.ecommercebitirme.entity.BasketProduct;
import com.aliak.ecommercebitirme.entity.FavoriteProduct;
import com.aliak.ecommercebitirme.requests.*;
import com.aliak.ecommercebitirme.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @PostMapping("/add")
    public BasketProduct addBasketProduct(@RequestBody AddBasketProductRequest basketProduct){
        return basketService.addBasketProduct(basketProduct);
    }

    @GetMapping("/{userId}")
    public List<BasketProduct> getAllBasketProductsById(@PathVariable Long userId){
        return basketService.getBasketProductsByUserId(userId);
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.POST })
    public String deleteBasketProduct(@RequestBody DeleteBasketProductRequest basketProduct)
    {
        basketService.deleteBasketProduct(basketProduct);
        return "Deleted Successfully.";
    }

    @GetMapping("/isProductInUsersBasket/{userId}/{productId}")
    public boolean isProductInUsersBasket(@PathVariable Long userId, @PathVariable Long productId){
        return basketService.isProductInUsersBasket(userId,productId);
    }

    @PostMapping("/updateBasketProductsQuantity")
    public BasketProduct updateBasketProductsQuantity(@RequestBody UpdateBasketProductsQuantityRequest quantityRequest){
        return basketService.updateBasketProductsQuantity(quantityRequest);
    }

    @DeleteMapping("/deleteAllBasketProductsOfUser/{userId}")
    public String deleteAllBasketProductsOfUser(@PathVariable Long userId)
    {
        basketService.deleteAllBasketProductsOfUser(userId);
        return "Deleted all basket products of user successfully!";
    }
}
