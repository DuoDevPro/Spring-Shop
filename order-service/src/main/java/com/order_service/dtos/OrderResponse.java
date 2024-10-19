package com.order_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String message;
//    private Long userId;

}
