package com.jangsuhyeon.shop.controller;

import com.google.gson.Gson;
import com.jangsuhyeon.shop.domain.dto.*;
import com.jangsuhyeon.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String goToProduct(@RequestParam(name = "category", defaultValue = "1")Long cateId, Model model) {

        // 카테고리 조회
        List<CategoryResponseDto> categoryList = shopService.findAllCategory();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("currentCategory", cateId);

        // 브랜드 조회
        List<BrandResponseDto> brandList = shopService.findAllBrand();
        model.addAttribute("brandList", brandList);

        // 상품의 최저가, 최고가 조회
        HashMap<String, Integer> priceRange = shopService.findByPrtPriceRange();
        model.addAttribute("priceRange", priceRange);

        // 해당 카테고리의 상품만 조회
        Pageable pageable = PageRequest.of(0,9);
        List<ProductResponseDto> productList = shopService.findAllByCateId(cateId, pageable);
        model.addAttribute("productList", productList);

        return "pages/shop/product";
    }

    // Todo 분석필요
    // 상품 조회 JSON
    @GetMapping("/product/json")
    public String getProductListAsJson (
            @RequestParam(value = "cateId") Long cateId,
            @RequestParam(value = "checkedBrand") String checkedBrandJson,
            @RequestParam(value = "maxPrice") int maxPrice,
            @RequestParam(value = "minPrice") int minPrice,
            Model model) {

        // JSON -> Long[]
        Long[] checkedBrands = new Gson().fromJson(checkedBrandJson, Long[].class);

        // 해당 카테고리 + 브랜드의 상품만 조회
        Pageable pageable = PageRequest.of(0,9);
        List<ProductResponseDto> productList = shopService.findByCateIdAndBrdIdInAndPrtPriceGreaterThanEqualAndPrtPriceLessThanEqual(cateId, checkedBrands, maxPrice, minPrice, pageable);
        model.addAttribute("productList", productList);

        return "pages/shop/product :: #product-list";
    }

    // 상품 상세 화면으로
    @GetMapping("/product/{prtId}")
    public String goToProductDetail(@PathVariable("prtId")Long prtId, Model model) {

        // 상품 상세정보 조회
        ProductResponseDto product = shopService.findByPrtId(prtId);
        model.addAttribute("product", product);

        return "pages/shop/detail";
    }

    // 장바구니 화면으로
    @GetMapping("/cart")
    public String goToCart(Model model) {

        // 장바구니 조회
        List<CartResponseDto> cartList = shopService.findCart();
        model.addAttribute("cartList", cartList);

        return "pages/shop/cart";
    }

    // 장바구니 추가
    @ResponseBody
    @PostMapping("/addToCart")
    public ResponseEntity addToCart(CartRequestDto product) {

        // 장바구니 추가
        shopService.addToCart(product);

        return ResponseEntity.ok("Success!");
    }

    // 결제
    @ResponseBody
    @PostMapping("/payment")
    public ResponseEntity payment(@RequestBody Map<String,String> orders) {
        System.out.println(orders);
        // Todo 장바구니 -> 주문내역 (X) 주문내역 저장으로
//        shopService.payment();

        return ResponseEntity.ok("Success!");
    }

    // 주문내역 화면으로
    @GetMapping("/order")
    public String goToOrder(Model model) {

        // 주문내역 조회
        List<PaymentResponseDto> paymentList = shopService.findPayment();
        model.addAttribute("paymentList", paymentList);

        return "pages/shop/order";
    }

}
