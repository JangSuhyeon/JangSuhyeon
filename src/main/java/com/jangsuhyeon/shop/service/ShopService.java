package com.jangsuhyeon.shop.service;

import com.jangsuhyeon.shop.domain.dto.*;
import com.jangsuhyeon.shop.domain.entity.*;
import com.jangsuhyeon.shop.repository.*;
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
    private final CartRepository cartRepository;
    private final PaymentRepository paymentRepository;

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
    public List<ProductResponseDto> findByCateIdAndBrdIdInAndPrtPriceGreaterThanEqualAndPrtPriceLessThanEqual(Long cateId, Long[] checkedBrands, int maxPrice, int minPrice, Pageable pageable) {

        // 등록순
        Page<Product> productList = productRepository.findByCateIdAndBrdIdInAndPrtPriceGreaterThanEqualAndPrtPriceLessThanEqual(cateId, checkedBrands, minPrice, maxPrice, pageable);

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

    // 상품 상세정보 조회
    public ProductResponseDto findByPrtId(Long prtId) {

        // 상품 상세정보 조회
        Product product = productRepository.findByPrtId(prtId);

        // Entity -> DTO
        ProductResponseDto productResponseDto = ProductResponseDto.toDto(product);

        return productResponseDto;
    }

    // 장바구니 추가
    public void addToCart(CartRequestDto product) {

        Cart cartProduct = cartRepository.findByPrtId(product.getPrtId());

        // 이미 장바구니에 있는 상품이라면 수량만 추가, 없으면 상품 추가
        if (cartProduct != null) {
            cartProduct.updateQty(cartProduct.getQty() + product.getQty());
            cartRepository.save(cartProduct);
        } else {
            // DTO -> Entity
            Cart cart = Cart.toEntity(product);
            cartRepository.save(cart);
        }
    }

    // 장바구니 조회
    public List<CartResponseDto> findCart() {
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        // 장바구니 조회
        List<Cart> cartList = cartRepository.findAll();

        // Entity -> DTO
        List<CartResponseDto> cartResponseDtoList = CartResponseDto.toDtoList(cartList);

        return cartResponseDtoList;
    }

    // 장바구니 -> 주문내역
    public void payment() {
        // 장바구니 조회
        List<Cart> cartList = cartRepository.findAll();

        // Cart -> Payment
        List<Payment> paymentList = new ArrayList<>();
        for (Cart cart : cartList) {
            Payment payment = Payment.builder()
                    .prtId(cart.getPrtId())
                    .prtPrice(cart.getProduct().getPrtPrice())
                    .qty(cart.getQty())
                    .totalAmt(cart.getProduct().getPrtPrice() * cart.getQty())
                    .build();
            paymentList.add(payment);
        }

        // 주문내역에 저장
        paymentRepository.saveAll(paymentList);

        // 장바구니 초기화
        cartRepository.deleteAll();
    }

    // 주문내역 조회
    public List<PaymentResponseDto> findPayment() {

        // 주문내역 조회
        List<Payment> paymentList = paymentRepository.findAll();

        // Entity -> DTO
        List<PaymentResponseDto> paymentResponseDtoList = PaymentResponseDto.toDtoList(paymentList);

        return paymentResponseDtoList;
    }
}
