package com.product_service.productMapper;

import com.product_service.dtos.ProductDto;
import com.product_service.dtos.request.ProductRequest;
import com.product_service.dtos.response.BuyProductResponse;
import com.product_service.dtos.response.ProductResponse;
import com.product_service.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .productName(request.getProductName())
                .productPrice(request.getProductPrice())
                .category(request.getCategory())
//                .productImage(request.getProductImage())
//                .category( Category.builder()
//                        .id(request.getCategory())
//                        .build())
//                .productStatus(request.getProductStatus())
                .productDescription(request.getProductDescription())
                .productBrand(request.getProductBrand())
//                .productId(request.)
                .availableQuantity(request.getAvailableQuantity())
                .build();
    }
    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
//                .productImage(product.getProductImage())
//                .productStatus(product.getProductStatus())
                .productDescription(product.getProductDescription())
                .availableQuantity(product.getAvailableQuantity())
//                .category(product.getCategory().getId())
                .build();


    }
    public ProductDto toProductDto(Product product) {

        return ProductDto.builder()
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .availableQuantity(product.getAvailableQuantity())
                .productDescription(product.getProductDescription())
                .productBrand(product.getProductBrand())
                .productId(product.getProductId())
                .category(product.getCategory())
                .id(product.getId())
                .build();
    }


    public BuyProductResponse toBuyProductResponse(Product product, double quantity) {

        return BuyProductResponse.builder()
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .availableQuantity(quantity)
                .id(product.getId())
                .productBrand(product.getProductBrand())
                .build();
    }

}
