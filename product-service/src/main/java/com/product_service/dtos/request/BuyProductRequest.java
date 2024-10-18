package com.product_service.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.Positive;


@Getter
@Builder
public class BuyProductRequest {

    @NotNull(message = "Product is mandatory")
    private Long id;

    @Positive(message = "Quantity is mandatory")
    private double availableQuantity;

}
