package com.jangsuhyeon.shop.domain.dto;

import com.jangsuhyeon.shop.domain.entity.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class CategoryResponseDto {

    private Long cateId;
    private String cateName;

    public static CategoryResponseDto toDto(Category category) {
        return CategoryResponseDto.builder()
                .cateId(category.getCateId())
                .cateName(category.getCateName())
                .build();
    }

    public static List<CategoryResponseDto> toDtoList(List<Category> categoryList) {

        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryResponseDto categoryResponseDto = toDto(category);
            categoryResponseDtoList.add(categoryResponseDto);
        }

        return categoryResponseDtoList;
    }

}
