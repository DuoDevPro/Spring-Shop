package com.product_service.service;

import com.product_service.dtos.ProductDto;
import com.product_service.dtos.request.BuyProductRequest;
import com.product_service.dtos.request.ProductRequest;
import com.product_service.dtos.response.BuyProductResponse;
import com.product_service.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {


//    public Integer createProduct(ProductRequest request);

    Product addProduct(ProductRequest request);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);

    List<BuyProductResponse> buyProducts(List<BuyProductRequest> request);

    List<Product> findAllByIdInOrderById(List<Long> ids);
}
