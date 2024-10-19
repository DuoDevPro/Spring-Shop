package com.order_service.controller;

import com.order_service.dtos.OrderDto;
import com.order_service.dtos.apiResponse.ApiResponse;
import com.order_service.entity.Order;
import com.order_service.exception.ResourceNotFoundException;
import com.order_service.mapper.OrderMapper;
import com.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/order")
    public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId) {
        try {
            Order order =  orderService.placeOrder(userId);
            OrderDto orderDto = orderMapper.toOrderDto(order);
            return ResponseEntity.ok(new ApiResponse("Item Order Success!", orderDto));
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error Occured!", e.getMessage()));
        }
    }

    @GetMapping("/{orderId}/order")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId) {
        try {
            OrderDto order = orderService.getOrder(orderId);
            return ResponseEntity.ok(new ApiResponse("Item Order Success!", order));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Oops!", e.getMessage()));
        }
    }

//    @GetMapping("/{userId}/order")
//    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId) {
//        try {
//            List<OrderDto> order = orderService.getUserOrders(userId);
//            return ResponseEntity.ok(new ApiResponse("Item Order Success!", order));
//        } catch (ResourceNotFoundException e) {
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Oops!", e.getMessage()));
//        }
//    }
}
