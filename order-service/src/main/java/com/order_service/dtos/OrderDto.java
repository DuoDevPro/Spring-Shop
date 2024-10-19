package com.order_service.dtos;

import com.order_service.entity.OrderItem;
import com.order_service.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class OrderDto {

//    @NotNull(message = "Customer should be present")
//    @NotEmpty(message = "Customer should be present")
//    @NotBlank(message = "Customer should be present")
//    private Long userId;

    private Long id;

    private String reference;

    @Positive(message = "Order amount should be positive")
    private BigDecimal totalAmount;

    @NotEmpty(message = "You should at least purchase one product")
    private Set<OrderItem> orderItems;

    private OrderStatus orderStatus;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

}
