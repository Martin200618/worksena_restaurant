package com.Trabajo.WorkSena.Orders.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "DTO para representar un item de una orden")
public class OrderItemDto {
    @Schema(description = "ID del item del menú", example = "1", required = true)
    private Long menuItemId;

    @Schema(description = "Nombre del item del menú", example = "Hamburguesa Clásica")
    private String menuItemName;

    @Schema(description = "Cantidad", example = "2", required = true)
    private Integer quantity;

    @Schema(description = "Precio unitario", example = "15000.00")
    private BigDecimal unitPrice;

    @Schema(description = "Precio total", example = "30000.00")
    private BigDecimal totalPrice;

    @Schema(description = "Notas del item", example = "Sin cebolla")
    private String notes;

    // Getters and Setters
    public Long getMenuItemId() { return menuItemId; }
    public void setMenuItemId(Long menuItemId) { this.menuItemId = menuItemId; }

    public String getMenuItemName() { return menuItemName; }
    public void setMenuItemName(String menuItemName) { this.menuItemName = menuItemName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
