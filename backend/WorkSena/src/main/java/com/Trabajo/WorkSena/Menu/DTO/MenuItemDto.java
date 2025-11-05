package com.Trabajo.WorkSena.Menu.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "DTO para representar un item del menú")
public class MenuItemDto {
    @Schema(description = "Nombre del item del menú", example = "Hamburguesa Clásica", required = true)
    private String name;

    @Schema(description = "Descripción del item", example = "Hamburguesa con carne de res, lechuga, tomate y queso")
    private String description;

    @Schema(description = "Precio del item", example = "15000.00", required = true)
    private BigDecimal price;

    @Schema(description = "URL de la imagen del item", example = "https://example.com/hamburguesa.jpg")
    private String imageUrl;

    @Schema(description = "Indica si el item está disponible", example = "true")
    private Boolean isAvailable;

    @Schema(description = "Tiempo de preparación en minutos", example = "15")
    private Integer preparationTime;

    @Schema(description = "ID de la categoría", example = "1", required = true)
    private Long categoryId;

    @Schema(description = "Nombre de la categoría", example = "Hamburguesas")
    private String categoryName;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }

    public Integer getPreparationTime() { return preparationTime; }
    public void setPreparationTime(Integer preparationTime) { this.preparationTime = preparationTime; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
