package com.jangsuhyeon.shop.controller;

import com.jangsuhyeon.shop.domain.dto.CategoryResponseDto;
import com.jangsuhyeon.shop.domain.dto.ProductResponseDto;
import com.jangsuhyeon.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    // 쇼핑몰 메인 화면으로
    @GetMapping("")
    public String goToShop(Model model) {

        // 메인에 노출될 상품 9개만 조회
        Pageable pageable = PageRequest.of(0, 9);
        List<ProductResponseDto> productList = shopService.findAll(pageable);
        model.addAttribute("productList", productList);

        return "pages/shop/index";
    }

    // product 화면으로
    @GetMapping("/product")
    public String goToProduct(@RequestParam(name = "category", defaultValue = "1")int cateId, Model model) {

        // 카테고리 조회
        List<CategoryResponseDto> categoryList = shopService.findAllCategory();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("currentCategory", cateId);

        // Todo 카테고리에 맞는 상품만 조회
        List<ProductResponseDto> productList = shopService.findAllByCateId(cateId);
        model.addAttribute("productList", productList);

        return "pages/shop/product";
    }

}
