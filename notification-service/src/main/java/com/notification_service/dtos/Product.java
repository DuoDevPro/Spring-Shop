package com.notification_service.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
