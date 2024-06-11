package com.aliak.ecommercebitirme.repository;

import com.aliak.ecommercebitirme.entity.Follower;
import com.aliak.ecommercebitirme.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower,Long> {

    List<Follower> findByFollowerUser(User followerUser);
    List<Follower> findByFollowedUser(User followedUser);
}
