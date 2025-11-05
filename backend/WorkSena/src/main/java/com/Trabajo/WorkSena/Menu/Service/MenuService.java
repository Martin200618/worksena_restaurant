package com.Trabajo.WorkSena.Menu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trabajo.WorkSena.Menu.DTO.CategoryDto;
import com.Trabajo.WorkSena.Menu.DTO.MenuItemDto;
import com.Trabajo.WorkSena.Menu.Entity.Category;
import com.Trabajo.WorkSena.Menu.Entity.MenuItem;
import com.Trabajo.WorkSena.Menu.Repository.ICategoryRepository;
import com.Trabajo.WorkSena.Menu.Repository.IMenuItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService implements IMenuService {

    @Autowired
    private IMenuItemRepository menuItemRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<MenuItemDto> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<MenuItemDto> getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
            .map(this::convertToDto);
    }

    @Override
    public MenuItemDto createMenuItem(MenuItem menuItem) {
        MenuItem savedItem = menuItemRepository.save(menuItem);
        return convertToDto(savedItem);
    }

    @Override
    public MenuItemDto updateMenuItem(Long id, MenuItem menuItemDetails) {
        MenuItem menuItem = menuItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("MenuItem not found"));

        menuItem.setName(menuItemDetails.getName());
        menuItem.setDescription(menuItemDetails.getDescription());
        menuItem.setPrice(menuItemDetails.getPrice());
        menuItem.setImageUrl(menuItemDetails.getImageUrl());
        menuItem.setIsAvailable(menuItemDetails.getIsAvailable());
        menuItem.setPreparationTime(menuItemDetails.getPreparationTime());
        menuItem.setCategory(menuItemDetails.getCategory());

        MenuItem updatedItem = menuItemRepository.save(menuItem);
        return convertToDto(updatedItem);
    }

    @Override
    public void deleteMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        menuItemRepository.delete(menuItem);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
            .map(this::convertCategoryToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .map(this::convertCategoryToDto);
    }

    @Override
    public CategoryDto createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category name already exists");
        }
        Category savedCategory = categoryRepository.save(category);
        return convertCategoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        category.setImageUrl(categoryDetails.getImageUrl());
        category.setIsActive(categoryDetails.getIsActive());
        category.setDisplayOrder(categoryDetails.getDisplayOrder());

        Category updatedCategory = categoryRepository.save(category);
        return convertCategoryToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public List<MenuItemDto> getAvailableMenuItems() {
        return menuItemRepository.findByIsAvailableTrue().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemDto> getMenuItemsByCategory(Long categoryId) {
        return menuItemRepository.findByCategoryIdAndIsAvailableTrue(categoryId).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemDto> searchMenuItems(String name) {
        return menuItemRepository.findByNameContainingIgnoreCase(name).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    private MenuItemDto convertToDto(MenuItem menuItem) {
        MenuItemDto dto = new MenuItemDto();
        dto.setName(menuItem.getName());
        dto.setDescription(menuItem.getDescription());
        dto.setPrice(menuItem.getPrice());
        dto.setImageUrl(menuItem.getImageUrl());
        dto.setIsAvailable(menuItem.getIsAvailable());
        dto.setPreparationTime(menuItem.getPreparationTime());
        if (menuItem.getCategory() != null) {
            dto.setCategoryId(menuItem.getCategory().getId());
            dto.setCategoryName(menuItem.getCategory().getName());
        }
        return dto;
    }

    private CategoryDto convertCategoryToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setImageUrl(category.getImageUrl());
        dto.setIsActive(category.getIsActive());
        dto.setDisplayOrder(category.getDisplayOrder());
        return dto;
    }
}
