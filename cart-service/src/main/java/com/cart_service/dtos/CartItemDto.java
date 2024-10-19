package com.cart_service.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {

    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String productId;
}
