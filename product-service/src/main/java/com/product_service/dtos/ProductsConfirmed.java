package com.product_service.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductsConfirmed {
   private Long productId;
   private String name;
   private String description;
   private BigDecimal price;
   private double quantity;
}
