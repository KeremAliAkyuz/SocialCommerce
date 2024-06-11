package com.aliak.ecommercebitirme.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "followers")
@NoArgsConstructor
@AllArgsConstructor
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OnDelete(action =  OnDeleteAction.CASCADE)//bir user silindiğinde favorilerine eklediği tüm productlar silinmeli.
    @ManyToOne(fetch = FetchType.EAGER)//bir kullanıcının birden fazla beğendiği product olabilir.
    @JoinColumn(name = "followedUserId",nullable = false)//User table'ındaki idyi foreign key olarak kullanarak iki tablo birleşir.
    private User followedUser;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OnDelete(action =  OnDeleteAction.CASCADE)//bir user silindiğinde favorilerine eklediği tüm productlar silinmeli.
    @ManyToOne(fetch = FetchType.EAGER)//bir kullanıcının birden fazla beğendiği product olabilir.
    @JoinColumn(name = "followerUserId",nullable = false)//User table'ındaki idyi foreign key olarak kullanarak iki tablo birleşir.
    private User followerUser;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;
}
