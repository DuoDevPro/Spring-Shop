package com.inventory_service.service;

import com.inventory_service.dtos.StockUpdateRequest;
import com.inventory_service.dtos.StockUpdateResponse;
import com.inventory_service.entity.Inventory;

import java.util.List;

public interface InventoryService {


    public boolean isProductAvailable(String productId, int quantity);

    public void reduceStock(String productId, double quantity);

    public List<StockUpdateRequest> isInStock(List<String> productId);

    public List<StockUpdateResponse> checkAndUpdateStock(List<StockUpdateRequest> stockUpdateRequests);

    public Inventory addInventory(String productId, double quantity);

}
