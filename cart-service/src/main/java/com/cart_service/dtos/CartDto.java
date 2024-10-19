package com.cart_service.dtos;

import com.cart_service.entity.CartItem;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
public class CartDto {
    private Long cartId;
    private Set<CartItem> items;
    private BigDecimal totalAmount;
    private Long userId;
}
