package com.order_service.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {

    private Long id;

    private double quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private Long productId;

    private Cart cart;


}
