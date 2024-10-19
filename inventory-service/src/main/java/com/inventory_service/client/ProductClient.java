package com.inventory_service.client;

import com.inventory_service.dtos.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{productId}")
    ProductDto getProductById(@PathVariable("productId") String productId);

    @GetMapping("/products/getAllById")
    List<ProductDto> findAllByIdInOrderById(List<String> productIds);
}
