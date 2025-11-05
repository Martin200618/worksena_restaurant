package com.Trabajo.WorkSena.Menu.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Trabajo.WorkSena.Menu.DTO.CategoryDto;
import com.Trabajo.WorkSena.Menu.DTO.MenuItemDto;
import com.Trabajo.WorkSena.Menu.Entity.Category;
import com.Trabajo.WorkSena.Menu.Entity.MenuItem;
import com.Trabajo.WorkSena.Menu.Service.IMenuService;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
@Tag(name = "Menu", description = "API para gestión del menú del restaurante")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    // MenuItem endpoints
    @GetMapping("/items")
    public ResponseEntity<List<MenuItemDto>> getAllMenuItems() {
        List<MenuItemDto> items = menuService.getAllMenuItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<MenuItemDto> getMenuItemById(@PathVariable Long id) {
        return menuService.getMenuItemById(id)
            .map(item -> ResponseEntity.ok(item))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/items")
    @Operation(summary = "Crear un nuevo item del menú", description = "Crea un nuevo item del menú con la información proporcionada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item del menú creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuItemDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content)
    })
    public ResponseEntity<MenuItemDto> createMenuItem(@RequestBody MenuItemDto menuItemDto) {
        try {
            // Convertir DTO a entidad
            MenuItem menuItem = new MenuItem();
            menuItem.setName(menuItemDto.getName());
            menuItem.setDescription(menuItemDto.getDescription());
            menuItem.setPrice(menuItemDto.getPrice());
            menuItem.setImageUrl(menuItemDto.getImageUrl());
            menuItem.setIsAvailable(menuItemDto.getIsAvailable() != null ? menuItemDto.getIsAvailable() : true);
            menuItem.setPreparationTime(menuItemDto.getPreparationTime());

            // Buscar categoría si se proporciona categoryId
            if (menuItemDto.getCategoryId() != null) {
                Category category = new Category();
                category.setId(menuItemDto.getCategoryId());
                menuItem.setCategory(category);
            }

            MenuItemDto createdItem = menuService.createMenuItem(menuItem);
            return ResponseEntity.ok(createdItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/items/{id}")
    @Operation(summary = "Actualizar un item del menú", description = "Actualiza la información de un item del menú existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item del menú actualizado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuItemDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content)
    })
    public ResponseEntity<MenuItemDto> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDto menuItemDto) {
        try {
            // Convertir DTO a entidad
            MenuItem menuItem = new MenuItem();
            menuItem.setName(menuItemDto.getName());
            menuItem.setDescription(menuItemDto.getDescription());
            menuItem.setPrice(menuItemDto.getPrice());
            menuItem.setImageUrl(menuItemDto.getImageUrl());
            menuItem.setIsAvailable(menuItemDto.getIsAvailable());
            menuItem.setPreparationTime(menuItemDto.getPreparationTime());

            // Buscar categoría si se proporciona categoryId
            if (menuItemDto.getCategoryId() != null) {
                Category category = new Category();
                category.setId(menuItemDto.getCategoryId());
                menuItem.setCategory(category);
            }

            MenuItemDto updatedItem = menuService.updateMenuItem(id, menuItem);
            return ResponseEntity.ok(updatedItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        try {
            menuService.deleteMenuItem(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Category endpoints
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = menuService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return menuService.getCategoryById(id)
            .map(category -> ResponseEntity.ok(category))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/categories")
    @Operation(summary = "Crear una nueva categoría", description = "Crea una nueva categoría del menú")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría creada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o categoría ya existe",
            content = @Content)
    })
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            // Convertir DTO a entidad
            Category category = new Category();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setImageUrl(categoryDto.getImageUrl());
            category.setIsActive(categoryDto.getIsActive() != null ? categoryDto.getIsActive() : true);
            category.setDisplayOrder(categoryDto.getDisplayOrder());

            CategoryDto createdCategory = menuService.createCategory(category);
            return ResponseEntity.ok(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/categories/{id}")
    @Operation(summary = "Actualizar una categoría", description = "Actualiza la información de una categoría existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content)
    })
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        try {
            // Convertir DTO a entidad
            Category category = new Category();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setImageUrl(categoryDto.getImageUrl());
            category.setIsActive(categoryDto.getIsActive());
            category.setDisplayOrder(categoryDto.getDisplayOrder());

            CategoryDto updatedCategory = menuService.updateCategory(id, category);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            menuService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Additional endpoints
    @GetMapping("/items/available")
    public ResponseEntity<List<MenuItemDto>> getAvailableMenuItems() {
        List<MenuItemDto> items = menuService.getAvailableMenuItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/items/category/{categoryId}")
    public ResponseEntity<List<MenuItemDto>> getMenuItemsByCategory(@PathVariable Long categoryId) {
        List<MenuItemDto> items = menuService.getMenuItemsByCategory(categoryId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/items/search")
    public ResponseEntity<List<MenuItemDto>> searchMenuItems(@RequestParam String name) {
        List<MenuItemDto> items = menuService.searchMenuItems(name);
        return ResponseEntity.ok(items);
    }
}
