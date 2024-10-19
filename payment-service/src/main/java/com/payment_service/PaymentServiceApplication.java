package com.payment_service;

import com.payment_service.enums.PaymentMethod;
import com.payment_service.kafka.NotificationProducer;
import com.payment_service.kafka.PaymentNotificationRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;

@SpringBootApplication
@EnableJpaAuditing
public class PaymentServiceApplication implements CommandLineRunner {


	private final NotificationProducer notificationProducer;

	// Constructor injection
	public PaymentServiceApplication(NotificationProducer notificationProducer) {
		this.notificationProducer = notificationProducer;
	}


	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		PaymentNotificationRequest request = new PaymentNotificationRequest();
		request.setAmount(new BigDecimal(3323));
		request.setPaymentMethod(PaymentMethod.COD);
		request.setCustomerEmail("efefefefef");
		request.setCustomerFirstname("pppoopppoopp");
		request.setCustomerLastname("rerererrer");
		request.setOrderReference("1st Order");

		// Use NotificationProducer to send a message
		notificationProducer.sendNotification(request);
	}
}
