package com.product_service.dtos.response;

import com.product_service.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String productName;

    private String productDescription;

    private String productImage;

    private double productPrice;

    private Integer category;

    private String productStatus;

    private double availableQuantity;
}
