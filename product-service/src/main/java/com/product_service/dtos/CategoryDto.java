package com.product_service.dtos;

import com.product_service.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {

    @NotBlank(message = "Category name is required")
    private String name;

    private List<Product> products;



}
