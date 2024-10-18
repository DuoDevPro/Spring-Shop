package com.product_service.dtos.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BuyProductResponse {

    private Long id;

    private String productName;

    private String productBrand;

    private String productDescription;

    private double productPrice;

    private double availableQuantity;

}
