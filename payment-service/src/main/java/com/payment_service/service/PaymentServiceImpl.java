package com.payment_service.service;

import com.payment_service.dtos.PaymentRequest;
import com.payment_service.kafka.NotificationProducer;
import com.payment_service.kafka.PaymentNotificationRequest;
import com.payment_service.mapper.PaymentMapper;
import com.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;


    @Override
    public Integer createPayment(PaymentRequest request) {
        var payment = this.repository.save(this.mapper.toPayment(request));

        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.getOrderReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        request.getCustomer().getFirstname(),
                        request.getCustomer().getLastname(),
                        request.getCustomer().getEmail()
                )
        );
        return payment.getId();
    }
}
