package com.jangsuhyeon.shop.domain.dto;

import com.jangsuhyeon.shop.domain.entity.Category;
import com.jangsuhyeon.shop.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
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

    public static List<ProductResponseDto> toDtoList(Page<Product> productList) {

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (Product product : productList) {
            ProductResponseDto productResponseDto = toDto(product);
            productResponseDtoList.add(productResponseDto);
        }

        return productResponseDtoList;
    }

}
