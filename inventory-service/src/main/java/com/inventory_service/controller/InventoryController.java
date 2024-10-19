package com.inventory_service.controller;

import com.inventory_service.dtos.StockUpdateRequest;
import com.inventory_service.dtos.StockUpdateResponse;
import com.inventory_service.entity.Inventory;
import com.inventory_service.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

//    @GetMapping("/check/{productId}/{quantity}")
//    public boolean isProductAvailable(@PathVariable String productId, @PathVariable int quantity) {
//        return inventoryService.isProductAvailable(productId, quantity);
//    }
//
//    @PostMapping("/reduce")
//    public void reduceStock(@RequestBody StockUpdateRequest stockUpdateRequest) {
//        inventoryService.reduceStock(stockUpdateRequest.getProductId(), stockUpdateRequest.getQuantity());
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<StockUpdateRequest> isInStock(@RequestParam List<String> productIds) {
//        return inventoryService.isInStock(productIds);
//    }


    @PostMapping("/check-and-update-stock")
    public List<StockUpdateResponse> checkAndUpdateStock(@RequestBody List<StockUpdateRequest> stockUpdateRequest) {
      return inventoryService.checkAndUpdateStock(stockUpdateRequest);
    }


    @PostMapping("/addInventory/{productId}/{quantity}")
    public Inventory addInventory(@PathVariable String productId, @PathVariable double quantity) {
       return inventoryService.addInventory(productId, quantity);
    }
}
