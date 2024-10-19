package com.order_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StockUpdateRequest {
    private Long productId;
    private double quantity;
//    private boolean isInStock;
}
