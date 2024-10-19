package com.cart_service.controller;


import com.cart_service.dtos.CartDto;
import com.cart_service.dtos.apiResponse.ApiResponse;
import com.cart_service.entity.Cart;
import com.cart_service.exception.ResourceNotFoundException;
import com.cart_service.mapper.CartMapper;
import com.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carts")

public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;


    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<ApiResponse<Cart>> getCart(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(new ApiResponse<>("Success", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }


    @PostMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart( @PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Clear Cart Success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalAmount( @PathVariable Long cartId) {
        try {
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Total Price", totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{cartId}/cart-for-order")
    public ResponseEntity<ApiResponse<CartDto>> getCartForOrder(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            CartDto cartDto = cartMapper.toDto(cart);
            return ResponseEntity.ok(new ApiResponse<>("Success", cartDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }
//
//    @GetMapping("/{cartId}/my-cart")
//    public Cart getCartById(@PathVariable Long cartId) {
//
//        return cartService.getCart(cartId);
//
//    }
}
