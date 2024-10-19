package com.cart_service.client;

import com.cart_service.dtos.ProductResponse;
import com.cart_service.dtos.apiResponse.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

//    @GetMapping("/products/get/{productId}")
//    ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable("productId") Long productId);

    @GetMapping("/products/getForCart/{productId}")
    ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long productId);
}