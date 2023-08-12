package com.jangsuhyeon.shop.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cateId;

    @Column
    private String cateName;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

}
