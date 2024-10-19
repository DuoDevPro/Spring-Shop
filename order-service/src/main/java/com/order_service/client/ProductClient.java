package com.order_service.client;

import com.order_service.dtos.ProductResponse;
import com.order_service.dtos.ProductsConfirmed;
import com.order_service.dtos.apiResponse.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{productId}")
    ProductResponse getProductById(@PathVariable("productId") Long productId);

    @GetMapping("/products/all/confirmedProducts")
    ResponseEntity<ApiResponse<List<ProductsConfirmed>>> getAllProductsByProductIdsForConfirm(List<Long> productIds);

    @PostMapping("/products/confirmedProducts")
    List<ProductsConfirmed> getProductListByIds(@RequestBody List<Long> productIds);
}
