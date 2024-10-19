package com.order_service.mapper;

import com.order_service.dtos.OrderDto;
import com.order_service.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderItems(order.getOrderItems())
                .totalAmount(order.getTotalAmount())
                .createdDate(order.getCreatedDate())
                .lastModifiedDate(order.getLastModifiedDate())
                .reference(order.getReference())
                .build();
    }



}
