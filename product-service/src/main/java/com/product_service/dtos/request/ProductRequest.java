package com.product_service.dtos.request;

import com.product_service.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

//    private Long id;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product description is required")
    private String productDescription;

    @NotBlank(message = "Product Brand is required")
    private String productBrand;


//    private String productImage;

    @Positive(message = "Price should be positive")
    private double productPrice;

    @NotNull(message = "Product category is required")
    private Category category;

//    private String productStatus;

    @Positive(message = "Available quantity should be positive")
    private double availableQuantity;

}
