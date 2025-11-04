package com.Trabajo.WorkSena.Inventory.Controller;

import com.Trabajo.WorkSena.Inventory.DTO.InventoryDto;
import com.Trabajo.WorkSena.Inventory.Entity.InventoryItem;
import com.Trabajo.WorkSena.Inventory.Service.IInventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InventoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IInventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
    }

    @Test
    void getAllInventoryItems_ShouldReturnList() throws Exception {
        List<InventoryDto> items = Arrays.asList(new InventoryDto(), new InventoryDto());
        when(inventoryService.getAllInventoryItems()).thenReturn(items);

        mockMvc.perform(get("/api/inventory"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getAllInventoryItems();
    }

    @Test
    void getInventoryItemById_ShouldReturnItem() throws Exception {
        InventoryDto item = new InventoryDto();
        item.setId(1L);
        when(inventoryService.getInventoryItemById(1L)).thenReturn(Optional.of(item));

        mockMvc.perform(get("/api/inventory/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getInventoryItemById(1L);
    }

    @Test
    void getInventoryItemById_ShouldReturnNotFound() throws Exception {
        when(inventoryService.getInventoryItemById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/inventory/1"))
                .andExpect(status().isNotFound());

        verify(inventoryService, times(1)).getInventoryItemById(1L);
    }

    @Test
    void getItemBySku_ShouldReturnItem() throws Exception {
        InventoryDto item = new InventoryDto();
        when(inventoryService.getItemBySku("SKU123")).thenReturn(Optional.of(item));

        mockMvc.perform(get("/api/inventory/sku/SKU123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getItemBySku("SKU123");
    }

    @Test
    void createInventoryItem_ShouldReturnCreatedItem() throws Exception {
        InventoryItem item = new InventoryItem();
        InventoryDto createdItem = new InventoryDto();
        when(inventoryService.createInventoryItem(any(InventoryItem.class))).thenReturn(createdItem);

        mockMvc.perform(post("/api/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).createInventoryItem(any(InventoryItem.class));
    }

    @Test
    void createInventoryItem_ShouldReturnBadRequest() throws Exception {
        when(inventoryService.createInventoryItem(any(InventoryItem.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(inventoryService, times(1)).createInventoryItem(any(InventoryItem.class));
    }

    @Test
    void updateInventoryItem_ShouldReturnUpdatedItem() throws Exception {
        InventoryDto updatedItem = new InventoryDto();
        when(inventoryService.updateInventoryItem(eq(1L), any(InventoryItem.class))).thenReturn(updatedItem);

        mockMvc.perform(put("/api/inventory/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).updateInventoryItem(eq(1L), any(InventoryItem.class));
    }

    @Test
    void deleteInventoryItem_ShouldReturnNoContent() throws Exception {
        doNothing().when(inventoryService).deleteInventoryItem(1L);

        mockMvc.perform(delete("/api/inventory/1"))
                .andExpect(status().isNoContent());

        verify(inventoryService, times(1)).deleteInventoryItem(1L);
    }

    @Test
    void deleteInventoryItem_ShouldReturnNotFound() throws Exception {
        doThrow(new RuntimeException()).when(inventoryService).deleteInventoryItem(1L);

        mockMvc.perform(delete("/api/inventory/1"))
                .andExpect(status().isNotFound());

        verify(inventoryService, times(1)).deleteInventoryItem(1L);
    }

    @Test
    void updateStock_ShouldReturnUpdatedItem() throws Exception {
        InventoryDto updatedItem = new InventoryDto();
        when(inventoryService.updateStock(eq(1L), any(BigDecimal.class))).thenReturn(updatedItem);

        mockMvc.perform(put("/api/inventory/1/stock")
                .param("stock", "10.5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).updateStock(eq(1L), any(BigDecimal.class));
    }

    @Test
    void addStock_ShouldReturnUpdatedItem() throws Exception {
        InventoryDto updatedItem = new InventoryDto();
        when(inventoryService.addStock(eq(1L), any(BigDecimal.class))).thenReturn(updatedItem);

        mockMvc.perform(put("/api/inventory/1/add-stock")
                .param("quantity", "5.0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).addStock(eq(1L), any(BigDecimal.class));
    }

    @Test
    void removeStock_ShouldReturnUpdatedItem() throws Exception {
        InventoryDto updatedItem = new InventoryDto();
        when(inventoryService.removeStock(eq(1L), any(BigDecimal.class))).thenReturn(updatedItem);

        mockMvc.perform(put("/api/inventory/1/remove-stock")
                .param("quantity", "2.0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).removeStock(eq(1L), any(BigDecimal.class));
    }

    @Test
    void getItemsByCategory_ShouldReturnList() throws Exception {
        List<InventoryDto> items = Arrays.asList(new InventoryDto());
        when(inventoryService.getItemsByCategory("Electronics")).thenReturn(items);

        mockMvc.perform(get("/api/inventory/category/Electronics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getItemsByCategory("Electronics");
    }

    @Test
    void getItemsBySupplier_ShouldReturnList() throws Exception {
        List<InventoryDto> items = Arrays.asList(new InventoryDto());
        when(inventoryService.getItemsBySupplier(1L)).thenReturn(items);

        mockMvc.perform(get("/api/inventory/supplier/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getItemsBySupplier(1L);
    }

    @Test
    void getItemsByStatus_ShouldReturnList() throws Exception {
        List<InventoryDto> items = Arrays.asList(new InventoryDto());
        when(inventoryService.getItemsByStatus(InventoryItem.InventoryStatus.ACTIVE)).thenReturn(items);

        mockMvc.perform(get("/api/inventory/status/ACTIVE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getItemsByStatus(InventoryItem.InventoryStatus.ACTIVE);
    }

    @Test
    void searchItemsByName_ShouldReturnList() throws Exception {
        List<InventoryDto> items = Arrays.asList(new InventoryDto());
        when(inventoryService.searchItemsByName("test")).thenReturn(items);

        mockMvc.perform(get("/api/inventory/search")
                .param("name", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).searchItemsByName("test");
    }

    @Test
    void getLowStockItems_ShouldReturnList() throws Exception {
        List<InventoryDto> items = Arrays.asList(new InventoryDto());
        when(inventoryService.getLowStockItems()).thenReturn(items);

        mockMvc.perform(get("/api/inventory/alerts/low-stock"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getLowStockItems();
    }

    @Test
    void getOutOfStockItems_ShouldReturnList() throws Exception {
        List<InventoryDto> items = Arrays.asList(new InventoryDto());
        when(inventoryService.getOutOfStockItems()).thenReturn(items);

        mockMvc.perform(get("/api/inventory/alerts/out-of-stock"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getOutOfStockItems();
    }

    @Test
    void getItemsNeedingRestock_ShouldReturnList() throws Exception {
        List<InventoryDto> items = Arrays.asList(new InventoryDto());
        when(inventoryService.getItemsNeedingRestock()).thenReturn(items);

        mockMvc.perform(get("/api/inventory/alerts/needs-restock"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getItemsNeedingRestock();
    }

    @Test
    void getItemsByCostRange_ShouldReturnList() throws Exception {
        List<InventoryDto> items = Arrays.asList(new InventoryDto());
        when(inventoryService.getItemsByCostRange(BigDecimal.valueOf(10), BigDecimal.valueOf(100))).thenReturn(items);

        mockMvc.perform(get("/api/inventory/cost-range")
                .param("minCost", "10")
                .param("maxCost", "100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).getItemsByCostRange(BigDecimal.valueOf(10), BigDecimal.valueOf(100));
    }

    @Test
    void activateItem_ShouldReturnActivatedItem() throws Exception {
        InventoryDto activatedItem = new InventoryDto();
        when(inventoryService.activateItem(1L)).thenReturn(activatedItem);

        mockMvc.perform(put("/api/inventory/1/activate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).activateItem(1L);
    }

    @Test
    void deactivateItem_ShouldReturnDeactivatedItem() throws Exception {
        InventoryDto deactivatedItem = new InventoryDto();
        when(inventoryService.deactivateItem(1L)).thenReturn(deactivatedItem);

        mockMvc.perform(put("/api/inventory/1/deactivate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).deactivateItem(1L);
    }

    @Test
    void markAsDiscontinued_ShouldReturnDiscontinuedItem() throws Exception {
        InventoryDto discontinuedItem = new InventoryDto();
        when(inventoryService.markAsDiscontinued(1L)).thenReturn(discontinuedItem);

        mockMvc.perform(put("/api/inventory/1/discontinue"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(inventoryService, times(1)).markAsDiscontinued(1L);
    }
}