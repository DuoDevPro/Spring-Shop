package com.cart_service.service;

import com.cart_service.entity.CartItem;

public interface CartItemService {

    void addItemToCart(Long cartId, Long productId, double quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, double quantity);
    CartItem getCartItem(Long cartId, Long productId);
}
