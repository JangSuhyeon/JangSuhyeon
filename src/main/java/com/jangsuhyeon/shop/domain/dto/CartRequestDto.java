package com.jangsuhyeon.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartRequestDto {

    private Long prtId;
    private int qty;

}
