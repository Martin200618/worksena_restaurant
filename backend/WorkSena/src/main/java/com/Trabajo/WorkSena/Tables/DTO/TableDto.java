package com.Trabajo.WorkSena.Tables.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para representar una mesa en el restaurante")
public class TableDto {
    @Schema(description = "Número de la mesa", example = "T001")
    private String tableNumber;

    @Schema(description = "Capacidad de la mesa", example = "4")
    private Integer capacity;

    @Schema(description = "Estado de la mesa", example = "AVAILABLE", allowableValues = {"AVAILABLE", "OCCUPIED", "RESERVED", "MAINTENANCE", "OUT_OF_SERVICE"})
    private String status;

    @Schema(description = "Ubicación de la mesa", example = "Patio")
    private String location;

    @Schema(description = "Descripción de la mesa", example = "Mesa cerca de la ventana")
    private String description;

    @Schema(description = "Indica si la mesa está activa", example = "true")
    private Boolean isActive;

    // Getters and Setters
    public String getTableNumber() { return tableNumber; }
    public void setTableNumber(String tableNumber) { this.tableNumber = tableNumber; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
