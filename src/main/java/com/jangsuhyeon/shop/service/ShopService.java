package com.jangsuhyeon.shop.service;

import com.jangsuhyeon.shop.domain.dto.ProductResponseDto;
import com.jangsuhyeon.shop.domain.entity.Product;
import com.jangsuhyeon.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopRepository shopRepository;

    // 전체 상품 조회
    public List<ProductResponseDto> findAll(Pageable pageable) {

        // 최신등록순
        Page<Product> productList = shopRepository.findAllByOrderByRegDtDesc(pageable);

        // Entity -> DTO
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (Product product : productList) {
            ProductResponseDto productResponseDto = ProductResponseDto.toDto(product);
            productResponseDtoList.add(productResponseDto);
        }

        return productResponseDtoList;
    }
}
