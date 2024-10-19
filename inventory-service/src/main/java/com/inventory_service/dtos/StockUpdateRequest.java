package com.inventory_service.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockUpdateRequest {
    private String productId;
    private double quantity;
    private boolean isInStock;

}
