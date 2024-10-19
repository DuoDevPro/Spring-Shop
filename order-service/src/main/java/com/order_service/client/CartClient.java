package com.order_service.client;

import com.order_service.dtos.Cart;
import com.order_service.dtos.apiResponse.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("/carts/{cartId}/cart-for-order")
    ResponseEntity<ApiResponse<Cart>> getCartForOrder(@PathVariable Long cartId);
        @PostMapping("/carts/{cartId}/clear")
    ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId);
}
