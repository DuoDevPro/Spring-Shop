package com.product_service.service;

import com.product_service.dtos.CategoryDto;
import com.product_service.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(CategoryDto category);
    Category updateCategory(CategoryDto category, Long id);
    void deleteCategoryById(Long id);
}
