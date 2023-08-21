package com.jangsuhyeon.shop.domain.dto;

import com.jangsuhyeon.shop.domain.entity.Payment;
import com.jangsuhyeon.shop.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class PaymentResponseDto {

    private Product product;
    private int prtPrice;
    private int qty;
    private int totalAmt;
    private LocalDateTime payDt;

    public static PaymentResponseDto toDto(Payment payment) {
        return PaymentResponseDto.builder()
                .product(payment.getProduct())
                .prtPrice(payment.getPrtPrice())
                .qty(payment.getQty())
                .totalAmt(payment.getTotalAmt())
                .payDt(payment.getPayDt())
                .build();
    }

    public static List<PaymentResponseDto> toDtoList(List<Payment> paymentList) {

        List<PaymentResponseDto> paymentResponseDtoList = new ArrayList<>();
        for (Payment payment : paymentList) {
            paymentResponseDtoList.add(toDto(payment));
        }
        return paymentResponseDtoList;

    }

}
