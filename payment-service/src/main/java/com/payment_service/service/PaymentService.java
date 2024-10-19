package com.payment_service.service;

import com.payment_service.dtos.PaymentRequest;

public interface PaymentService {
    Integer createPayment(PaymentRequest request);
}
