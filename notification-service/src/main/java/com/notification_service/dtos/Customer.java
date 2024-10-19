package com.notification_service.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
public class Customer {
    String id;
    String firstname;
    String lastname;
    String email;
}
