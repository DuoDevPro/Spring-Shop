package com.order_service.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class Cart {


//    private Long id;
//
//    private BigDecimal totalAmount = BigDecimal.ZERO;
//
//    private Set<CartItem> items = new HashSet<>();
//
//    private Long userId;


    private Long cartId;
    private Set<CartItem> items;
    private BigDecimal totalAmount;
    private Long userId;

}
