package com.notification_service.service;

import com.notification_service.dtos.Product;
import jakarta.mail.MessagingException;

import java.math.BigDecimal;
import java.util.List;

public interface EmailService {

    void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException;

    void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException;
}
