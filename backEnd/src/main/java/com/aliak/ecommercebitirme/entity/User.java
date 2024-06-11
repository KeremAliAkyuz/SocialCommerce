package com.aliak.ecommercebitirme.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "profileIntro")
    private String profileIntro;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name="joinDate")
    private LocalDateTime joinDate;

    @Column(name="channelName")
    private String channelName;

    @Column(name="photo")
    private String photo;

    @Column(name="Age")
    private String age;

    @Column(name="mail")
    private String mail;

    @Column(name="balance")
    private Long balance;
}
