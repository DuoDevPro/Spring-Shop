package com.notification_service.dtos;

import com.notification_service.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {
   private String orderReference;
   private BigDecimal totalAmount;
   private PaymentMethod paymentMethod;
   private Customer customer;
   private List<Product> products;
}
