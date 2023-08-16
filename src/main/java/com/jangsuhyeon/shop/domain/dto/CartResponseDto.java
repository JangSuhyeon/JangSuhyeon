package com.jangsuhyeon.shop.domain.dto;

import com.jangsuhyeon.shop.domain.entity.Cart;
import com.jangsuhyeon.shop.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class CartResponseDto {

    private Product product;
    private int qty;

    public static CartResponseDto toDto(Cart cart) {
        return CartResponseDto.builder()
                .product(cart.getProduct())
                .qty(cart.getQty())
                .build();
    }

    public static List<CartResponseDto> toDtoList(List<Cart> cartList) {

        List<CartResponseDto> cartResponseDtoList = new ArrayList<>();
        for (Cart cart : cartList) {
            CartResponseDto cartResponseDto = toDto(cart);
            cartResponseDtoList.add(cartResponseDto);
        }

        return cartResponseDtoList;
    }
}
