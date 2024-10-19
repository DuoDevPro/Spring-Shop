package com.cart_service.mapper;

import com.cart_service.dtos.CartDto;
import com.cart_service.entity.Cart;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    public CartDto toDto(Cart cart) {
        return CartDto.builder()
                .cartId(cart.getId())
                .items(cart.getItems())
                .totalAmount(cart.getTotalAmount())
                .userId(cart.getUserId())
                .build();
    }
}
