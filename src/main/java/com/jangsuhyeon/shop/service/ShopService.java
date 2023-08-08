package com.jangsuhyeon.shop.service;

import com.jangsuhyeon.shop.domain.dto.BrandResponseDto;
import com.jangsuhyeon.shop.domain.dto.CategoryResponseDto;
import com.jangsuhyeon.shop.domain.dto.ProductResponseDto;
import com.jangsuhyeon.shop.domain.entity.Brand;
import com.jangsuhyeon.shop.domain.entity.Category;
import com.jangsuhyeon.shop.domain.entity.Product;
import com.jangsuhyeon.shop.repository.BrandRepository;
import com.jangsuhyeon.shop.repository.CategoryRepository;
import com.jangsuhyeon.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

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
        List<Category> categoryList = categoryRepository.findAllByOrderByCateId();

        // Entity -> DTO
        List<CategoryResponseDto> categoryResponseDtoList  = CategoryResponseDto.toDtoList(categoryList);

        return categoryResponseDtoList;
    }

    // 해당 카테고리의 상품 조회
    public List<ProductResponseDto> findAllByCateId(Long cateId, Pageable pageable) {

        // 최신등록순
        Page<Product> productList = productRepository.findAllByCateIdOrderByRegDtDesc(cateId, pageable);

        // Entity -> DTO
        List<ProductResponseDto> productResponseDtoList = ProductResponseDto.toDtoList(productList);

        return productResponseDtoList;
    }

    // 브랜드 조회
    public List<BrandResponseDto> findAllBrand() {

        // 등록순
        List<Brand> brandList = brandRepository.findAllByOrderByBrdId();

        // Entity -> DTO
        List<BrandResponseDto> brandResponseDtoList = BrandResponseDto.toDtoList(brandList);

        return brandResponseDtoList;
    }

    // 해당 카테고리 + 브랜드의 상품만 조회
    public List<ProductResponseDto> findByCateIdAndBrdIdIn(Long cateId, Long[] checkedBrands, Pageable pageable) {

        // 등록순
        Page<Product> productList = productRepository.findByCateIdAndBrdIdIn(cateId, checkedBrands, pageable);

        // Entity -> DTO
        List<ProductResponseDto> productResponseDtoList = ProductResponseDto.toDtoList(productList);

        return productResponseDtoList;
    }

    // 상품의 최저가, 최고가 조회
    public HashMap<String, Integer> findByPrtPriceRange() {

        HashMap<String, Integer> priceRange = new HashMap<>();
        priceRange.put("max", productRepository.findMaxPrtPrice());
        priceRange.put("min", productRepository.findMinPrtPrice());

        return priceRange;
    }
}
