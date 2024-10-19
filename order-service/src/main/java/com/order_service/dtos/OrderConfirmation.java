package com.order_service.dtos;

import com.order_service.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderConfirmation {

    String orderReference;
    BigDecimal totalAmount;
    PaymentMethod paymentMethod;
    Customer customer;
    List<ProductsConfirmed> products;
}
