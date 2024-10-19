package com.payment_service.mapper;

import com.payment_service.dtos.PaymentRequest;
import com.payment_service.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        if (request == null) {
            return null;
        }
        return Payment.builder()
                .id(request.getId())
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .orderId(request.getOrderId())
                .build();
    }
}
