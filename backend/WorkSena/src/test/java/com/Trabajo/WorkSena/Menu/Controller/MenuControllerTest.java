package com.Trabajo.WorkSena.Menu.Controller;

import com.Trabajo.WorkSena.Menu.DTO.CategoryDto;
import com.Trabajo.WorkSena.Menu.DTO.MenuItemDto;
import com.Trabajo.WorkSena.Menu.Entity.Category;
import com.Trabajo.WorkSena.Menu.Entity.MenuItem;
import com.Trabajo.WorkSena.Menu.Service.IMenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MenuControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IMenuService menuService;

    @InjectMocks
    private MenuController menuController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
    }

    @Test
    void getAllMenuItems_ShouldReturnList() throws Exception {
        List<MenuItemDto> items = Arrays.asList(new MenuItemDto(), new MenuItemDto());
        when(menuService.getAllMenuItems()).thenReturn(items);

        mockMvc.perform(get("/api/menu/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).getAllMenuItems();
    }

    @Test
    void getMenuItemById_ShouldReturnItem() throws Exception {
        MenuItemDto item = new MenuItemDto();
        when(menuService.getMenuItemById(1L)).thenReturn(java.util.Optional.of(item));

        mockMvc.perform(get("/api/menu/items/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).getMenuItemById(1L);
    }

    @Test
    void getMenuItemById_ShouldReturnNotFound() throws Exception {
        when(menuService.getMenuItemById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/menu/items/1"))
                .andExpect(status().isNotFound());

        verify(menuService, times(1)).getMenuItemById(1L);
    }

    @Test
    void createMenuItem_ShouldReturnCreatedItem() throws Exception {
        MenuItemDto createdItem = new MenuItemDto();
        when(menuService.createMenuItem(any(MenuItem.class))).thenReturn(createdItem);

        mockMvc.perform(post("/api/menu/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).createMenuItem(any(MenuItem.class));
    }

    @Test
    void createMenuItem_ShouldReturnBadRequest() throws Exception {
        when(menuService.createMenuItem(any(MenuItem.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/menu/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(menuService, times(1)).createMenuItem(any(MenuItem.class));
    }

    @Test
    void updateMenuItem_ShouldReturnUpdatedItem() throws Exception {
        MenuItemDto updatedItem = new MenuItemDto();
        when(menuService.updateMenuItem(eq(1L), any(MenuItem.class))).thenReturn(updatedItem);

        mockMvc.perform(put("/api/menu/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).updateMenuItem(eq(1L), any(MenuItem.class));
    }

    @Test
    void updateMenuItem_ShouldReturnBadRequest() throws Exception {
        when(menuService.updateMenuItem(eq(1L), any(MenuItem.class))).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/menu/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(menuService, times(1)).updateMenuItem(eq(1L), any(MenuItem.class));
    }

    @Test
    void deleteMenuItem_ShouldReturnNoContent() throws Exception {
        doNothing().when(menuService).deleteMenuItem(1L);

        mockMvc.perform(delete("/api/menu/items/1"))
                .andExpect(status().isNoContent());

        verify(menuService, times(1)).deleteMenuItem(1L);
    }

    @Test
    void deleteMenuItem_ShouldReturnNotFound() throws Exception {
        doThrow(new RuntimeException()).when(menuService).deleteMenuItem(1L);

        mockMvc.perform(delete("/api/menu/items/1"))
                .andExpect(status().isNotFound());

        verify(menuService, times(1)).deleteMenuItem(1L);
    }

    @Test
    void getAllCategories_ShouldReturnList() throws Exception {
        List<CategoryDto> categories = Arrays.asList(new CategoryDto(), new CategoryDto());
        when(menuService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/menu/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).getAllCategories();
    }

    @Test
    void getCategoryById_ShouldReturnCategory() throws Exception {
        CategoryDto category = new CategoryDto();
        when(menuService.getCategoryById(1L)).thenReturn(java.util.Optional.of(category));

        mockMvc.perform(get("/api/menu/categories/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).getCategoryById(1L);
    }

    @Test
    void getCategoryById_ShouldReturnNotFound() throws Exception {
        when(menuService.getCategoryById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/menu/categories/1"))
                .andExpect(status().isNotFound());

        verify(menuService, times(1)).getCategoryById(1L);
    }

    @Test
    void createCategory_ShouldReturnCreatedCategory() throws Exception {
        CategoryDto createdCategory = new CategoryDto();
        when(menuService.createCategory(any(Category.class))).thenReturn(createdCategory);

        mockMvc.perform(post("/api/menu/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).createCategory(any(Category.class));
    }

    @Test
    void createCategory_ShouldReturnBadRequest() throws Exception {
        when(menuService.createCategory(any(Category.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/menu/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(menuService, times(1)).createCategory(any(Category.class));
    }

    @Test
    void updateCategory_ShouldReturnUpdatedCategory() throws Exception {
        CategoryDto updatedCategory = new CategoryDto();
        when(menuService.updateCategory(eq(1L), any(Category.class))).thenReturn(updatedCategory);

        mockMvc.perform(put("/api/menu/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).updateCategory(eq(1L), any(Category.class));
    }

    @Test
    void updateCategory_ShouldReturnBadRequest() throws Exception {
        when(menuService.updateCategory(eq(1L), any(Category.class))).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/menu/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(menuService, times(1)).updateCategory(eq(1L), any(Category.class));
    }

    @Test
    void deleteCategory_ShouldReturnNoContent() throws Exception {
        doNothing().when(menuService).deleteCategory(1L);

        mockMvc.perform(delete("/api/menu/categories/1"))
                .andExpect(status().isNoContent());

        verify(menuService, times(1)).deleteCategory(1L);
    }

    @Test
    void deleteCategory_ShouldReturnNotFound() throws Exception {
        doThrow(new RuntimeException()).when(menuService).deleteCategory(1L);

        mockMvc.perform(delete("/api/menu/categories/1"))
                .andExpect(status().isNotFound());

        verify(menuService, times(1)).deleteCategory(1L);
    }

    @Test
    void getAvailableMenuItems_ShouldReturnList() throws Exception {
        List<MenuItemDto> items = Arrays.asList(new MenuItemDto());
        when(menuService.getAvailableMenuItems()).thenReturn(items);

        mockMvc.perform(get("/api/menu/items/available"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).getAvailableMenuItems();
    }

    @Test
    void getMenuItemsByCategory_ShouldReturnList() throws Exception {
        List<MenuItemDto> items = Arrays.asList(new MenuItemDto());
        when(menuService.getMenuItemsByCategory(1L)).thenReturn(items);

        mockMvc.perform(get("/api/menu/items/category/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).getMenuItemsByCategory(1L);
    }

    @Test
    void searchMenuItems_ShouldReturnList() throws Exception {
        List<MenuItemDto> items = Arrays.asList(new MenuItemDto());
        when(menuService.searchMenuItems("pizza")).thenReturn(items);

        mockMvc.perform(get("/api/menu/items/search")
                .param("name", "pizza"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(menuService, times(1)).searchMenuItems("pizza");
    }
}