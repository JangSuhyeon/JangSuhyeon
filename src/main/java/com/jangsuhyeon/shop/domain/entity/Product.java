package com.jangsuhyeon.shop.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prtId;

    @Column
    private String prtName;

    @Column
    private int prtPrice;

    @Column
    private String prtImgUrl;

    @Column
    private Long cateId;

    @Column
    private Long brdId;

    @Column
    private LocalDateTime regDt;

    @ManyToOne
    @JoinColumn(name = "cateId", insertable = false, updatable = false)
    private Category category;

    @PrePersist
    public void setRegDt() {
        this.regDt = LocalDateTime.now();
    }

}
