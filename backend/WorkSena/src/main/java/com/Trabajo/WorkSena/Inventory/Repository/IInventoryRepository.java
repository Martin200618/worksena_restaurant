package com.Trabajo.WorkSena.Inventory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Trabajo.WorkSena.Inventory.Entity.InventoryItem;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IInventoryRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByCategory(String category);
    List<InventoryItem> findBySupplierId(Long supplierId);
    List<InventoryItem> findByStatus(InventoryItem.InventoryStatus status);
    List<InventoryItem> findByIsActiveTrue();

    @Query("SELECT i FROM InventoryItem i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<InventoryItem> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT i FROM InventoryItem i WHERE i.sku = :sku")
    InventoryItem findBySku(@Param("sku") String sku);

    @Query("SELECT i FROM InventoryItem i WHERE i.currentStock <= i.minimumStock AND i.isActive = true")
    List<InventoryItem> findLowStockItems();

    @Query("SELECT i FROM InventoryItem i WHERE i.currentStock <= 0 AND i.isActive = true")
    List<InventoryItem> findOutOfStockItems();

    @Query("SELECT i FROM InventoryItem i WHERE i.currentStock < i.maximumStock AND i.isActive = true")
    List<InventoryItem> findItemsNeedingRestock();

    @Query("SELECT i FROM InventoryItem i WHERE i.unitCost BETWEEN :minCost AND :maxCost")
    List<InventoryItem> findByUnitCostRange(@Param("minCost") BigDecimal minCost, @Param("maxCost") BigDecimal maxCost);

    boolean existsBySku(String sku);
}
