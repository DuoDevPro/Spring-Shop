package com.inventory_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockUpdateResponse {

    private String productId;
    private double quantity;
    private boolean isInStock;
}
