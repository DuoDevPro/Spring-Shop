package com.product_service.repository;

import com.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryName(String category);

    List<Product> findByProductBrand(String brand);

    List<Product> findByCategoryNameAndProductBrand(String category, String brand);

    List<Product> findByProductName(String name);

    List<Product> findByProductBrandAndProductName(String brand, String name);

    Long countByProductBrandAndProductName(String brand, String name);

    List<Product> findAllByIdInOrderById(List<Long> productIds);
}
