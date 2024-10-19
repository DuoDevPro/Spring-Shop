package com.inventory_service.mapper;

import com.inventory_service.dtos.StockUpdateResponse;
import com.inventory_service.entity.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {

    public StockUpdateResponse toStockUpdateResponse(Inventory inventory, double quantity) {

        return StockUpdateResponse.builder()
                .isInStock(inventory.getAvailableQuantity() > quantity)
                .productId(inventory.getProductId())
                .quantity(quantity)
                .build();
    }
}
