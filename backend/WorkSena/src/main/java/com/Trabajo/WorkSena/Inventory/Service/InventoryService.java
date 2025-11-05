package com.Trabajo.WorkSena.Inventory.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trabajo.WorkSena.Inventory.DTO.InventoryDto;
import com.Trabajo.WorkSena.Inventory.Entity.InventoryItem;
import com.Trabajo.WorkSena.Inventory.Repository.IInventoryRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService implements IInventoryService {

    @Autowired
    private IInventoryRepository inventoryRepository;

    @Override
    public List<InventoryDto> getAllInventoryItems() {
        return inventoryRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<InventoryDto> getInventoryItemById(Long id) {
        return inventoryRepository.findById(id)
            .map(this::convertToDto);
    }

    @Override
    public InventoryDto createInventoryItem(InventoryDto itemDto) {
        InventoryItem item = convertToEntity(itemDto);
        if (inventoryRepository.existsBySku(item.getSku())) {
            throw new RuntimeException("SKU already exists");
        }
        InventoryItem savedItem = inventoryRepository.save(item);
        return convertToDto(savedItem);
    }

    @Override
    public InventoryDto updateInventoryItem(Long id, InventoryDto itemDetails) {
        InventoryItem item = inventoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        // Check if SKU is being changed and if it already exists
        if (!item.getSku().equals(itemDetails.getSku()) &&
            inventoryRepository.existsBySku(itemDetails.getSku())) {
            throw new RuntimeException("SKU already exists");
        }

        item.setName(itemDetails.getName());
        item.setDescription(itemDetails.getDescription());
        item.setSku(itemDetails.getSku());
        item.setCategory(itemDetails.getCategory());
        item.setUnit(itemDetails.getUnit());
        item.setMinimumStock(itemDetails.getMinimumStock());
        item.setMaximumStock(itemDetails.getMaximumStock());
        item.setUnitCost(itemDetails.getUnitCost());
        item.setSupplierId(itemDetails.getSupplierId());
        item.setLocation(itemDetails.getLocation());
        item.setIsActive(itemDetails.getIsActive());

        InventoryItem updatedItem = inventoryRepository.save(item);
        return convertToDto(updatedItem);
    }

    @Override
    public void deleteInventoryItem(Long id) {
        InventoryItem item = inventoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventory item not found"));
        inventoryRepository.delete(item);
    }

    @Override
    public InventoryDto updateStock(Long id, BigDecimal newStock) {
        InventoryItem item = inventoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        if (newStock.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Stock cannot be negative");
        }

        item.setCurrentStock(newStock);
        InventoryItem updatedItem = inventoryRepository.save(item);
        return convertToDto(updatedItem);
    }

    @Override
    public InventoryDto addStock(Long id, BigDecimal quantity) {
        InventoryItem item = inventoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        BigDecimal currentStock = item.getCurrentStock() != null ? item.getCurrentStock() : BigDecimal.ZERO;
        BigDecimal newStock = currentStock.add(quantity);

        if (newStock.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Resulting stock cannot be negative");
        }

        item.setCurrentStock(newStock);
        InventoryItem updatedItem = inventoryRepository.save(item);
        return convertToDto(updatedItem);
    }

    @Override
    public InventoryDto removeStock(Long id, BigDecimal quantity) {
        return addStock(id, quantity.negate());
    }

    @Override
    public List<InventoryDto> getItemsByCategory(String category) {
        return inventoryRepository.findByCategory(category).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDto> getItemsBySupplier(Long supplierId) {
        return inventoryRepository.findBySupplierId(supplierId).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDto> getItemsByStatus(InventoryItem.InventoryStatus status) {
        return inventoryRepository.findByStatus(status).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDto> searchItemsByName(String name) {
        return inventoryRepository.findByNameContainingIgnoreCase(name).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<InventoryDto> getItemBySku(String sku) {
        InventoryItem item = inventoryRepository.findBySku(sku);
        return item != null ? Optional.of(convertToDto(item)) : Optional.empty();
    }

    @Override
    public List<InventoryDto> getLowStockItems() {
        return inventoryRepository.findLowStockItems().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDto> getOutOfStockItems() {
        return inventoryRepository.findOutOfStockItems().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDto> getItemsNeedingRestock() {
        return inventoryRepository.findItemsNeedingRestock().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDto> getItemsByCostRange(BigDecimal minCost, BigDecimal maxCost) {
        return inventoryRepository.findByUnitCostRange(minCost, maxCost).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public InventoryDto activateItem(Long id) {
        InventoryItem item = inventoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventory item not found"));
        item.setIsActive(true);
        item.setStatus(InventoryItem.InventoryStatus.ACTIVE);
        InventoryItem updatedItem = inventoryRepository.save(item);
        return convertToDto(updatedItem);
    }

    @Override
    public InventoryDto deactivateItem(Long id) {
        InventoryItem item = inventoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventory item not found"));
        item.setIsActive(false);
        item.setStatus(InventoryItem.InventoryStatus.INACTIVE);
        InventoryItem updatedItem = inventoryRepository.save(item);
        return convertToDto(updatedItem);
    }

    @Override
    public InventoryDto markAsDiscontinued(Long id) {
        InventoryItem item = inventoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventory item not found"));
        item.setStatus(InventoryItem.InventoryStatus.DISCONTINUED);
        InventoryItem updatedItem = inventoryRepository.save(item);
        return convertToDto(updatedItem);
    }

    private InventoryDto convertToDto(InventoryItem item) {
        InventoryDto dto = new InventoryDto();
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setSku(item.getSku());
        dto.setCategory(item.getCategory());
        dto.setUnit(item.getUnit());
        dto.setCurrentStock(item.getCurrentStock());
        dto.setMinimumStock(item.getMinimumStock());
        dto.setMaximumStock(item.getMaximumStock());
        dto.setUnitCost(item.getUnitCost());
        dto.setSupplierId(item.getSupplierId());
        dto.setLocation(item.getLocation());
        dto.setStatus(item.getStatus().toString());
        dto.setIsActive(item.getIsActive());
        return dto;
    }

    private InventoryItem convertToEntity(InventoryDto dto) {
        InventoryItem item = new InventoryItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setSku(dto.getSku());
        item.setCategory(dto.getCategory());
        item.setUnit(dto.getUnit());
        item.setCurrentStock(dto.getCurrentStock());
        item.setMinimumStock(dto.getMinimumStock());
        item.setMaximumStock(dto.getMaximumStock());
        item.setUnitCost(dto.getUnitCost());
        item.setSupplierId(dto.getSupplierId());
        item.setLocation(dto.getLocation());
        if (dto.getStatus() != null) {
            item.setStatus(InventoryItem.InventoryStatus.valueOf(dto.getStatus()));
        }
        item.setIsActive(dto.getIsActive());
        return item;
    }
}
