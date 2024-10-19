package com.inventory_service.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDto {
    private Long id;

    private String productId;

    private String productName;

    private String productBrand;

    private String productDescription;

//    private List<ImageDto> productImages;

    private double productPrice;

    private double availableQuantity;


}
