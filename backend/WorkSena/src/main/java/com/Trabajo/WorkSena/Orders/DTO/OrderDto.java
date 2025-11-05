package com.Trabajo.WorkSena.Orders.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "DTO para representar una orden en el restaurante")
public class OrderDto {
    @Schema(description = "Número de orden", example = "ORD-001")
    private String orderNumber;

    @Schema(description = "ID de la mesa", example = "1", required = true)
    private Long tableId;

    @Schema(description = "Nombre del cliente", example = "Juan Pérez")
    private String customerName;

    @Schema(description = "Teléfono del cliente", example = "+57 300 123 4567")
    private String customerPhone;

    @Schema(description = "Estado de la orden", example = "PENDING", allowableValues = {"PENDING", "CONFIRMED", "PREPARING", "READY", "DELIVERED", "CANCELLED"}, required = true)
    private String status;

    @Schema(description = "Monto total de la orden", example = "45000.00")
    private BigDecimal totalAmount;

    @Schema(description = "Notas adicionales", example = "Cliente pidió sin cebolla")
    private String notes;

    @Schema(description = "Lista de items de la orden")
    private List<OrderItemDto> items;

    // Getters and Setters
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public Long getTableId() { return tableId; }
    public void setTableId(Long tableId) { this.tableId = tableId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<OrderItemDto> getItems() { return items; }
    public void setItems(List<OrderItemDto> items) { this.items = items; }
}
