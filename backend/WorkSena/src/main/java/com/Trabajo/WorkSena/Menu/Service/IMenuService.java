package com.Trabajo.WorkSena.Menu.Service;

import java.util.List;
import java.util.Optional;

import com.Trabajo.WorkSena.Menu.DTO.CategoryDto;
import com.Trabajo.WorkSena.Menu.DTO.MenuItemDto;
import com.Trabajo.WorkSena.Menu.Entity.Category;
import com.Trabajo.WorkSena.Menu.Entity.MenuItem;

public interface IMenuService {
    // MenuItem operations
    List<MenuItemDto> getAllMenuItems();
    Optional<MenuItemDto> getMenuItemById(Long id);
    MenuItemDto createMenuItem(MenuItem menuItem);
    MenuItemDto updateMenuItem(Long id, MenuItem menuItemDetails);
    void deleteMenuItem(Long id);

    // Category operations
    List<CategoryDto> getAllCategories();
    Optional<CategoryDto> getCategoryById(Long id);
    CategoryDto createCategory(Category category);
    CategoryDto updateCategory(Long id, Category categoryDetails);
    void deleteCategory(Long id);

    // Additional operations
    List<MenuItemDto> getAvailableMenuItems();
    List<MenuItemDto> getMenuItemsByCategory(Long categoryId);
    List<MenuItemDto> searchMenuItems(String name);
}
