package com.jangsuhyeon.shop.domain.dto;

import com.jangsuhyeon.shop.domain.entity.Product;
import lombok.Builder;

@Builder
public class ProductResponseDto {

    private Long prtId;
    private String prtName;
    private int prtPrice;
    private String prtImgUrl;

    public static ProductResponseDto toDto(Product product) {
        return ProductResponseDto.builder()
                .prtId(product.getPrtId())
                .prtName(product.getPrtName())
                .prtPrice(product.getPrtPrice())
                .prtImgUrl(product.getPrtImgUrl())
                .build();
    }

}
