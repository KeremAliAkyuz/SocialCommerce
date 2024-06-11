package com.aliak.ecommercebitirme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "BasketProducts")
@NoArgsConstructor
@AllArgsConstructor
public class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OnDelete(action =  OnDeleteAction.CASCADE)//bir user silindiğinde favorilerine eklediği tüm productlar silinmeli.
    @ManyToOne(fetch = FetchType.LAZY)//bir kullanıcının birden fazla beğendiği product olabilir.
    @JoinColumn(name = "userId",nullable = false)//User table'ındaki idyi foreign key olarak kullanarak iki tablo birleşir.
    private User user;

    @Column(name = "basketProductId")
    private Long basketProductId;

    @Column(name = "quantity")
    private Long quantity;

}
