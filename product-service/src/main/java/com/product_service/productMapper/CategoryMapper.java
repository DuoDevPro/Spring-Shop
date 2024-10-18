package com.product_service.productMapper;

import com.product_service.dtos.CategoryDto;
import com.product_service.dtos.ImageDto;
import com.product_service.entity.Category;
import com.product_service.entity.Image;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category toImageDto(CategoryDto dto) {
        return Category.builder()
                .name(dto.getName())
                .products(dto.getProducts())
                .build();
    }
}
