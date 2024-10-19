package com.order_service.client;

import com.order_service.dtos.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {


    @PostMapping("/payments")
    Integer requestOrderPayment(@RequestBody PaymentRequest request);

}
