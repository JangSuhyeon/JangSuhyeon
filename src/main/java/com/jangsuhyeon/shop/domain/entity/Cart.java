package com.jangsuhyeon.shop.domain.entity;

import com.jangsuhyeon.shop.domain.dto.CartRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cart {

//    Todo product_prt_id는 왜 생겼는지..?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catId;

    @Column
    private Long prtId;

    @Column
    private int qty;

    @OneToOne(mappedBy = "cart")
    private Product product;

    public static Cart toEntity(CartRequestDto product) {
        return Cart.builder()
                .prtId(product.getPrtId())
                .qty(product.getQty())
                .build();
    }

    // 장바구니 수량 변경
    public void updateQty(int qty) {
        this.qty = qty;
    }
}
