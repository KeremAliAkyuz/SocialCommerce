package com.aliak.ecommercebitirme.repository;

import com.aliak.ecommercebitirme.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
