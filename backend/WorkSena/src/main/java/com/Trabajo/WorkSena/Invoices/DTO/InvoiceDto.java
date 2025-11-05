package com.Trabajo.WorkSena.Invoices.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO para representar una factura en el restaurante")
public class InvoiceDto {
    @Schema(description = "Número de la factura", example = "INV-001", required = true)
    private String invoiceNumber;

    @Schema(description = "ID de la orden asociada", example = "1", required = true)
    private Long orderId;

    @Schema(description = "Nombre del cliente", example = "Juan Pérez")
    private String customerName;

    @Schema(description = "Teléfono del cliente", example = "+57 300 123 4567")
    private String customerPhone;

    @Schema(description = "Número de mesa", example = "5")
    private Integer tableNumber;

    @Schema(description = "Subtotal de la factura", example = "30000.0")
    private BigDecimal subtotal;

    @Schema(description = "Tasa de impuesto", example = "0.19")
    private BigDecimal taxRate;

    @Schema(description = "Monto del impuesto", example = "5700.0")
    private BigDecimal taxAmount;

    @Schema(description = "Monto total de la factura", example = "35700.0")
    private BigDecimal totalAmount;

    @Schema(description = "Estado de la factura", example = "PENDING", allowableValues = {"PENDING", "PAID", "CANCELLED", "OVERDUE"})
    private String status;

    @Schema(description = "Fecha de emisión", example = "2023-10-01T12:00:00")
    private LocalDateTime issuedAt;

    @Schema(description = "Fecha de vencimiento", example = "2023-10-01T23:59:59")
    private LocalDateTime dueDate;

    @Schema(description = "Notas adicionales", example = "Factura generada automáticamente")
    private String notes;

    @Schema(description = "Lista de items de la factura")
    private List<InvoiceItemDto> items;

    // Getters and Setters
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public Integer getTableNumber() { return tableNumber; }
    public void setTableNumber(Integer tableNumber) { this.tableNumber = tableNumber; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getTaxRate() { return taxRate; }
    public void setTaxRate(BigDecimal taxRate) { this.taxRate = taxRate; }

    public BigDecimal getTaxAmount() { return taxAmount; }
    public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<InvoiceItemDto> getItems() { return items; }
    public void setItems(List<InvoiceItemDto> items) { this.items = items; }
}
