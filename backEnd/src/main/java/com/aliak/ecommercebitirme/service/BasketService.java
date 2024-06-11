package com.aliak.ecommercebitirme.service;

import com.aliak.ecommercebitirme.entity.BasketProduct;
import com.aliak.ecommercebitirme.entity.FavoriteProduct;
import com.aliak.ecommercebitirme.entity.User;
import com.aliak.ecommercebitirme.repository.BasketRepository;
import com.aliak.ecommercebitirme.requests.AddBasketProductRequest;
import com.aliak.ecommercebitirme.requests.DeleteBasketProductRequest;
import com.aliak.ecommercebitirme.requests.UpdateBasketProductsQuantityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final UserService userService;

    public BasketProduct addBasketProduct(AddBasketProductRequest basketProduct)
    {
        User user = userService.getOneUser(basketProduct.getUserId());

        BasketProduct toSave = new BasketProduct();
        toSave.setBasketProductId(basketProduct.getBasketProductId());
        toSave.setQuantity(basketProduct.getQuantity());
        toSave.setUser(user);

        return basketRepository.save(toSave);
    }
    public BasketProduct updateBasketProductsQuantity(UpdateBasketProductsQuantityRequest quantityRequest){
        BasketProduct toUpdateProduct = basketRepository.findByUserIdAndBasketProductId(quantityRequest.getUserId(),quantityRequest.getBasketProductId());
        toUpdateProduct.setQuantity(quantityRequest.getQuantity());
        return basketRepository.save(toUpdateProduct);
    }
    public List<BasketProduct> getBasketProductsByUserId(Long userId) {
        return basketRepository.findByUserId(userId);
    }
    public void deleteBasketProduct(DeleteBasketProductRequest basketProduct) {
        List<BasketProduct> basketProducts = basketRepository.findByUserId(basketProduct.getUserId());
        List<BasketProduct> deletedProducts = basketProducts.stream().filter(fp -> Objects.equals(fp.getBasketProductId(), basketProduct.getBasketProductId())).toList();

        basketRepository.deleteAll(deletedProducts);
    }
    public void deleteAllBasketProductsOfUser(Long userID){
        List<BasketProduct> toDeleteBasketProducts = basketRepository.findByUserId(userID);
        basketRepository.deleteAll(toDeleteBasketProducts);
    }
    public boolean isProductInUsersBasket(Long userId,Long productId){
        List<BasketProduct> usersBasket = basketRepository.findByUserId(userId);
        return usersBasket.stream().anyMatch(bp -> Objects.equals(bp.getBasketProductId(), productId));
    }
}
