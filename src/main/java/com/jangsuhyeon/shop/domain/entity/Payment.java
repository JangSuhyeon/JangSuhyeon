package com.jangsuhyeon.shop.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payId;

    @Column
    private Long prtId;

    @Column
    private int prtPrice;

    @Column
    private int qty;

    @Column
    private int totalAmt;

    @Column
    private LocalDateTime payDt;

    @PrePersist
    public void setPayDt(){
        this.payDt = LocalDateTime.now();
    }

}
