package com.order_service.service;

import com.order_service.dtos.OrderDto;
import com.order_service.dtos.OrderResponse;
import com.order_service.entity.Order;

import java.util.List;

public interface OrderService {

    public OrderResponse placeOrder1(String productId, int quantity) ;

//    public List<OrderResponse> findAllOrders();

//    public OrderResponse findById(Integer id);

//    public Integer createOrder(OrderRequest request);

    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
//    List<OrderDto> getUserOrders(Long userId);

}
