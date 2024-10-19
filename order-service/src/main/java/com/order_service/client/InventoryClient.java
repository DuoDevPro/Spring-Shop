package com.order_service.client;

import com.order_service.dtos.StockUpdateRequest;
import com.order_service.dtos.StockUpdateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory/check/{productId}/{quantity}")
    boolean isProductAvailable(@PathVariable("productId") String productId, @PathVariable("quantity") int quantity);

    @PostMapping("/inventory/reduce")
    void reduceStock(@RequestBody StockUpdateRequest stockUpdateRequest);

//    void checkAndUpdate(List<StockUpdateRequest> stockUpdateRequests);

    @PostMapping("/inventory/check-and-update-stock")
    List<StockUpdateResponse> checkAndUpdateStock(@RequestBody List<StockUpdateRequest> stockUpdateRequest);
}
