package com.jangsuhyeon.shop.domain.dto;

import com.jangsuhyeon.shop.domain.entity.Brand;
import com.jangsuhyeon.shop.domain.entity.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class BrandResponseDto {

    private Long brdId;
    private String brdName;

    public static BrandResponseDto toDto(Brand brand) {
        return BrandResponseDto.builder()
                .brdId(brand.getBrdId())
                .brdName(brand.getBrdNm())
                .build();
    }

    public static List<BrandResponseDto> toDtoList(List<Brand> brandList) {

        List<BrandResponseDto> brandResponseDtoList = new ArrayList<>();
        for (Brand brand : brandList) {
            BrandResponseDto brandResponseDto = toDto(brand);
            brandResponseDtoList.add(brandResponseDto);
        }

        return brandResponseDtoList;
    }

}
