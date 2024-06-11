package com.aliak.ecommercebitirme.service;

import com.aliak.ecommercebitirme.entity.User;
import com.aliak.ecommercebitirme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Long setUsersBalance(Long userId,Long balance) {
        User user = userRepository.findById(userId).orElse(null);
        user.setBalance(balance);
        userRepository.save(user);
        return balance;
    }
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

}
