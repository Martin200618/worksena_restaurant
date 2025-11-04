package com.Trabajo.WorkSena.Inventory.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.Trabajo.WorkSena.Inventory.DTO.InventoryDto;
import com.Trabajo.WorkSena.Inventory.Entity.InventoryItem;

public interface IInventoryService {
    List<InventoryDto> getAllInventoryItems();
    Optional<InventoryDto> getInventoryItemById(Long id);
    InventoryDto createInventoryItem(InventoryItem item);
    InventoryDto updateInventoryItem(Long id, InventoryItem itemDetails);
    void deleteInventoryItem(Long id);

    // Stock management
    InventoryDto updateStock(Long id, BigDecimal newStock);
    InventoryDto addStock(Long id, BigDecimal quantity);
    InventoryDto removeStock(Long id, BigDecimal quantity);

    // Filtering operations
    List<InventoryDto> getItemsByCategory(String category);
    List<InventoryDto> getItemsBySupplier(Long supplierId);
    List<InventoryDto> getItemsByStatus(InventoryItem.InventoryStatus status);
    List<InventoryDto> searchItemsByName(String name);
    Optional<InventoryDto> getItemBySku(String sku);

    // Alert operations
    List<InventoryDto> getLowStockItems();
    List<InventoryDto> getOutOfStockItems();
    List<InventoryDto> getItemsNeedingRestock();

    // Cost operations
    List<InventoryDto> getItemsByCostRange(BigDecimal minCost, BigDecimal maxCost);

    // Business operations
    InventoryDto activateItem(Long id);
    InventoryDto deactivateItem(Long id);
    InventoryDto markAsDiscontinued(Long id);
}
