package com.Trabajo.WorkSena.Menu.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para representar una categoría del menú")
public class CategoryDto {
    @Schema(description = "Nombre de la categoría", example = "Hamburguesas", required = true)
    private String name;

    @Schema(description = "Descripción de la categoría", example = "Platos principales con hamburguesas")
    private String description;

    @Schema(description = "URL de la imagen de la categoría", example = "https://example.com/hamburguesas.jpg")
    private String imageUrl;

    @Schema(description = "Indica si la categoría está activa", example = "true")
    private Boolean isActive;

    @Schema(description = "Orden de visualización", example = "1")
    private Integer displayOrder;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
}