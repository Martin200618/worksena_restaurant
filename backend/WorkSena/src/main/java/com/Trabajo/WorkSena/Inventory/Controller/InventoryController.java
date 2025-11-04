package com.Trabajo.WorkSena.Inventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Trabajo.WorkSena.Inventory.DTO.InventoryDto;
import com.Trabajo.WorkSena.Inventory.Entity.InventoryItem;
import com.Trabajo.WorkSena.Inventory.Service.IInventoryService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAllInventoryItems() {
        List<InventoryDto> items = inventoryService.getAllInventoryItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getInventoryItemById(@PathVariable Long id) {
        return inventoryService.getInventoryItemById(id)
            .map(item -> ResponseEntity.ok(item))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<InventoryDto> getItemBySku(@PathVariable String sku) {
        return inventoryService.getItemBySku(sku)
            .map(item -> ResponseEntity.ok(item))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventoryDto> createInventoryItem(@RequestBody InventoryItem item) {
        try {
            InventoryDto createdItem = inventoryService.createInventoryItem(item);
            return ResponseEntity.ok(createdItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItem itemDetails) {
        try {
            InventoryDto updatedItem = inventoryService.updateInventoryItem(id, itemDetails);
            return ResponseEntity.ok(updatedItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        try {
            inventoryService.deleteInventoryItem(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Stock management endpoints
    @PutMapping("/{id}/stock")
    public ResponseEntity<InventoryDto> updateStock(@PathVariable Long id, @RequestParam BigDecimal stock) {
        try {
            InventoryDto updatedItem = inventoryService.updateStock(id, stock);
            return ResponseEntity.ok(updatedItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/add-stock")
    public ResponseEntity<InventoryDto> addStock(@PathVariable Long id, @RequestParam BigDecimal quantity) {
        try {
            InventoryDto updatedItem = inventoryService.addStock(id, quantity);
            return ResponseEntity.ok(updatedItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/remove-stock")
    public ResponseEntity<InventoryDto> removeStock(@PathVariable Long id, @RequestParam BigDecimal quantity) {
        try {
            InventoryDto updatedItem = inventoryService.removeStock(id, quantity);
            return ResponseEntity.ok(updatedItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Filtering endpoints
    @GetMapping("/category/{category}")
    public ResponseEntity<List<InventoryDto>> getItemsByCategory(@PathVariable String category) {
        List<InventoryDto> items = inventoryService.getItemsByCategory(category);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<InventoryDto>> getItemsBySupplier(@PathVariable Long supplierId) {
        List<InventoryDto> items = inventoryService.getItemsBySupplier(supplierId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<InventoryDto>> getItemsByStatus(@PathVariable InventoryItem.InventoryStatus status) {
        List<InventoryDto> items = inventoryService.getItemsByStatus(status);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<List<InventoryDto>> searchItemsByName(@RequestParam String name) {
        List<InventoryDto> items = inventoryService.searchItemsByName(name);
        return ResponseEntity.ok(items);
    }

    // Alert endpoints
    @GetMapping("/alerts/low-stock")
    public ResponseEntity<List<InventoryDto>> getLowStockItems() {
        List<InventoryDto> items = inventoryService.getLowStockItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/alerts/out-of-stock")
    public ResponseEntity<List<InventoryDto>> getOutOfStockItems() {
        List<InventoryDto> items = inventoryService.getOutOfStockItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/alerts/needs-restock")
    public ResponseEntity<List<InventoryDto>> getItemsNeedingRestock() {
        List<InventoryDto> items = inventoryService.getItemsNeedingRestock();
        return ResponseEntity.ok(items);
    }

    // Cost range endpoint
    @GetMapping("/cost-range")
    public ResponseEntity<List<InventoryDto>> getItemsByCostRange(
            @RequestParam BigDecimal minCost,
            @RequestParam BigDecimal maxCost) {
        List<InventoryDto> items = inventoryService.getItemsByCostRange(minCost, maxCost);
        return ResponseEntity.ok(items);
    }

    // Status change endpoints
    @PutMapping("/{id}/activate")
    public ResponseEntity<InventoryDto> activateItem(@PathVariable Long id) {
        try {
            InventoryDto activatedItem = inventoryService.activateItem(id);
            return ResponseEntity.ok(activatedItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<InventoryDto> deactivateItem(@PathVariable Long id) {
        try {
            InventoryDto deactivatedItem = inventoryService.deactivateItem(id);
            return ResponseEntity.ok(deactivatedItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/discontinue")
    public ResponseEntity<InventoryDto> markAsDiscontinued(@PathVariable Long id) {
        try {
            InventoryDto discontinuedItem = inventoryService.markAsDiscontinued(id);
            return ResponseEntity.ok(discontinuedItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
