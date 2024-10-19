package com.order_service.dtos;

import com.order_service.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentRequest {
   private BigDecimal amount;
   private PaymentMethod paymentMethod;
   private Long orderId;
   private String orderReference;
   private Customer user;
}
