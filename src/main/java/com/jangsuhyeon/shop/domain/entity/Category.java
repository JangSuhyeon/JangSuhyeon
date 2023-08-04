package com.jangsuhyeon.shop.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cateId;

    @Column
    private String cateName;

}
