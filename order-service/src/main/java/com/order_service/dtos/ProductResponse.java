package com.order_service.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private Long productId;
    private String name;
    private double price;

}
