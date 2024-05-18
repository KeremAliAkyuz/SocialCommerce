package com.aliak.ecommercebitirme.controller;

import com.aliak.ecommercebitirme.entity.BasketProduct;
import com.aliak.ecommercebitirme.entity.User;
import com.aliak.ecommercebitirme.requests.UpdateBasketProductsQuantityRequest;
import com.aliak.ecommercebitirme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        return userService.getOneUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    @PostMapping("/{userId}/{balance}")
    public Long updateUsersBalance(@PathVariable Long userId,@PathVariable Long balance){
        return userService.setUsersBalance(userId,balance);
    }
}
