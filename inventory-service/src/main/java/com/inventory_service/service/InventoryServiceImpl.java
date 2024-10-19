package com.inventory_service.service;

import com.inventory_service.client.ProductClient;
import com.inventory_service.dtos.StockUpdateRequest;
import com.inventory_service.dtos.StockUpdateResponse;
import com.inventory_service.entity.Inventory;
import com.inventory_service.exception.InsufficientStockException;
import com.inventory_service.mapper.InventoryMapper;
import com.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;
    private final InventoryMapper inventoryMapper;

    @Override
    public boolean isProductAvailable(String productId, int quantity) {
        var inventory = inventoryRepository.findByProductId(productId);
        return inventory.isPresent() && inventory.get().getAvailableQuantity() >= quantity;
    }

    @Override
    @Transactional
    public void reduceStock(String productId, double quantity) {
        var inventory = inventoryRepository.findByProductId(productId);
        if (inventory.isEmpty() || inventory.get().getAvailableQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock for product ID: " + productId);
        }
        inventory.get().setAvailableQuantity(inventory.get().getAvailableQuantity() - quantity);
        inventoryRepository.save(inventory.get());
    }
    @Transactional(readOnly = true)
    @Override
    public List<StockUpdateRequest> isInStock(List<String> productId) {
      return inventoryRepository.findByProductIdIn(productId).stream()
                .map(inventory ->
                        StockUpdateRequest.builder()
                                .productId(inventory.getProductId())
                                .quantity(inventory.getAvailableQuantity())
                                .isInStock(inventory.getAvailableQuantity() > 0)
                                .build()
                ).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<StockUpdateResponse> checkAndUpdateStock(List<StockUpdateRequest> requests) {

        var productIds = requests
                .stream()
                .map(StockUpdateRequest::getProductId)
                .toList();

//        var storedProducts = productClient.findAllByIdInOrderById(productIds);

        var sortedInventoryByProductId = inventoryRepository.findAllByProductIdInOrderByProductId(productIds);

        if (productIds.size() != sortedInventoryByProductId.size()) {
            throw new InsufficientStockException("One or more products does not exist");
        }

//        if (productIds.size() != storedProducts.size()) {
//            throw new InsufficientStockException("One or more products does not exist");
//        }
        var sortedRequest = requests
                .stream()
                .sorted(Comparator.comparing(StockUpdateRequest::getProductId))
                .toList();
        var purchasedProducts = new ArrayList<StockUpdateResponse>();

        for (int i = 0; i < sortedInventoryByProductId.size(); i++) {
            var inventory = sortedInventoryByProductId.get(i);
            var productRequest = sortedRequest.get(i);
            if (inventory.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock quantity for product with ID:: " + productRequest.getProductId());
            }
            var newAvailableQuantity = inventory.getAvailableQuantity() - productRequest.getQuantity();
            inventory.setAvailableQuantity(newAvailableQuantity);
            inventoryRepository.save(inventory);
            purchasedProducts.add(inventoryMapper.toStockUpdateResponse(inventory, productRequest.getQuantity()));
        }
        return purchasedProducts;
    }

    @Override
    public Inventory addInventory(String productId, double quantity) {

        return inventoryRepository.findByProductId(productId)
                .map(existingInventory -> {
                    existingInventory.setAvailableQuantity(existingInventory.getAvailableQuantity() + quantity);
                    return inventoryRepository.save(existingInventory);
                })
                .orElseGet(() -> inventoryRepository.save(Inventory.builder()
                        .productId(productId)
                        .availableQuantity(quantity)
                        .build()));
    }
}
