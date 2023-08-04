package com.jangsuhyeon.shop.service;

import com.jangsuhyeon.shop.domain.dto.CategoryResponseDto;
import com.jangsuhyeon.shop.domain.dto.ProductResponseDto;
import com.jangsuhyeon.shop.domain.entity.Category;
import com.jangsuhyeon.shop.domain.entity.Product;
import com.jangsuhyeon.shop.repository.CategoryRepository;
import com.jangsuhyeon.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // 전체 상품 조회
    public List<ProductResponseDto> findAll(Pageable pageable) {

        // 최신등록순
        Page<Product> productList = productRepository.findAllByOrderByRegDtDesc(pageable);

        // Entity -> DTO
        List<ProductResponseDto> productResponseDtoList = ProductResponseDto.toDtoList(productList);

        return productResponseDtoList;
    }

    // 카테고리 조회
    public List<CategoryResponseDto> findAllCategory() {

        // 등록순
        Sort sort = Sort.by(Sort.Direction.ASC, "cateId");
        List<Category> categoryList = categoryRepository.findAll(sort);

        // Entity -> DTO
        List<CategoryResponseDto> categoryResponseDtoList  = CategoryResponseDto.toDtoList(categoryList);

        return categoryResponseDtoList;
    }

    // 해당 카테고리의 상품 조회
    public List<ProductResponseDto> findAllByCateId(int cateId) {

        // 등록순
        Sort sort = Sort.by(Sort.Direction.DESC, "regDt");
        Page<Product> productList = productRepository.findAllByCateId(cateId);

        // Entity -> DTO
        List<ProductResponseDto> productResponseDtoList = ProductResponseDto.toDtoList(productList);

        return productResponseDtoList;
    }
}
