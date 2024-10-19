package com.cart_service.service;

import com.cart_service.entity.Cart;

import java.math.BigDecimal;

public interface CartService {

    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();

    Cart getCartByUserId(Long userId);
}
