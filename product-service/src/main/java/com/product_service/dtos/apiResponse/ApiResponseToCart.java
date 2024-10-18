package com.product_service.dtos.apiResponse;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponseToCart<T> {
    private String message;
    private Object data;
}
