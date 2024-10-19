package com.notification_service.dtos;

import com.notification_service.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConfirmation {
   private String orderReference;
   private BigDecimal amount;
   private PaymentMethod paymentMethod;
   private String customerFirstname;
   private String customerLastname;
   private String customerEmail;
}
