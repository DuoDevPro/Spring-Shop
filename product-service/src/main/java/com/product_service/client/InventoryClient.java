package com.product_service.client;

import com.product_service.dtos.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "inventory-service")
@Service
public interface InventoryClient {

    @PostMapping("/inventory/addInventory/{productId}/{quantity}")
    Inventory addInventory(@PathVariable String productId, @PathVariable double quantity);

}