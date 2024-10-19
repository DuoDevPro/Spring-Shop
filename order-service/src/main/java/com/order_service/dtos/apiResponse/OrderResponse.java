package com.order_service.dtos.apiResponse;

import com.order_service.entity.OrderItem;
import com.order_service.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
public class OrderResponse {

    private Long id;

    private String reference;

    private BigDecimal totalAmount;

//    private Set<OrderItem> orderItems;

    private OrderStatus orderStatus;

//    private Long userId;

}
