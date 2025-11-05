package com.Trabajo.WorkSena.Inventory.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "DTO para representar un item del inventario")
public class InventoryDto {
    @Schema(description = "Nombre del item", example = "Tomates", required = true)
    private String name;

    @Schema(description = "Descripción del item", example = "Tomates frescos para ensaladas")
    private String description;

    @Schema(description = "Código SKU del item", example = "TOM-001", required = true)
    private String sku;

    @Schema(description = "Categoría del item", example = "Vegetales")
    private String category;

    @Schema(description = "Unidad de medida", example = "kg")
    private String unit;

    @Schema(description = "Stock actual", example = "50.0", required = true)
    private BigDecimal currentStock;

    @Schema(description = "Stock mínimo", example = "10.0")
    private BigDecimal minimumStock;

    @Schema(description = "Stock máximo", example = "100.0")
    private BigDecimal maximumStock;

    @Schema(description = "Costo unitario", example = "2000.0")
    private BigDecimal unitCost;

    @Schema(description = "ID del proveedor", example = "1")
    private Long supplierId;

    @Schema(description = "Ubicación del item", example = "Refrigerador A")
    private String location;

    @Schema(description = "Estado del item", example = "AVAILABLE", allowableValues = {"AVAILABLE", "LOW_STOCK", "OUT_OF_STOCK", "DISCONTINUED"})
    private String status;

    @Schema(description = "Indica si el item está activo", example = "true")
    private Boolean isActive;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public BigDecimal getCurrentStock() { return currentStock; }
    public void setCurrentStock(BigDecimal currentStock) { this.currentStock = currentStock; }

    public BigDecimal getMinimumStock() { return minimumStock; }
    public void setMinimumStock(BigDecimal minimumStock) { this.minimumStock = minimumStock; }

    public BigDecimal getMaximumStock() { return maximumStock; }
    public void setMaximumStock(BigDecimal maximumStock) { this.maximumStock = maximumStock; }

    public BigDecimal getUnitCost() { return unitCost; }
    public void setUnitCost(BigDecimal unitCost) { this.unitCost = unitCost; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
