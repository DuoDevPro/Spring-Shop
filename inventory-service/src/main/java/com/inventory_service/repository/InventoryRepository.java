package com.inventory_service.repository;

import com.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
//    Inventory findByProductId(String productId);


    Optional<Inventory> findByProductId(String productId);

    List<Inventory> findByProductIdIn(List<String> productId);
    List<Inventory> findAllByProductIdInOrderByProductId(List<String> productId);
}