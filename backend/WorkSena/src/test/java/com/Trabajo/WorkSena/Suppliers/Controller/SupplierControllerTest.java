package com.Trabajo.WorkSena.Suppliers.Controller;

import com.Trabajo.WorkSena.Suppliers.DTO.SupplierDto;
import com.Trabajo.WorkSena.Suppliers.Entity.Supplier;
import com.Trabajo.WorkSena.Suppliers.Service.ISupplierService;
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

class SupplierControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ISupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
    }

    @Test
    void getAllSuppliers_ShouldReturnList() throws Exception {
        List<SupplierDto> suppliers = Arrays.asList(new SupplierDto(), new SupplierDto());
        when(supplierService.getAllSuppliers()).thenReturn(suppliers);

        mockMvc.perform(get("/api/suppliers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void getSupplierById_ShouldReturnSupplier() throws Exception {
        SupplierDto supplier = new SupplierDto();
        when(supplierService.getSupplierById(1L)).thenReturn(java.util.Optional.of(supplier));

        mockMvc.perform(get("/api/suppliers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).getSupplierById(1L);
    }

    @Test
    void getSupplierById_ShouldReturnNotFound() throws Exception {
        when(supplierService.getSupplierById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/suppliers/1"))
                .andExpect(status().isNotFound());

        verify(supplierService, times(1)).getSupplierById(1L);
    }

    @Test
    void getSupplierByName_ShouldReturnSupplier() throws Exception {
        SupplierDto supplier = new SupplierDto();
        when(supplierService.getSupplierByName("Supplier A")).thenReturn(java.util.Optional.of(supplier));

        mockMvc.perform(get("/api/suppliers/name/Supplier A"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).getSupplierByName("Supplier A");
    }

    @Test
    void getSupplierByName_ShouldReturnNotFound() throws Exception {
        when(supplierService.getSupplierByName("Supplier A")).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/suppliers/name/Supplier A"))
                .andExpect(status().isNotFound());

        verify(supplierService, times(1)).getSupplierByName("Supplier A");
    }

    @Test
    void createSupplier_ShouldReturnCreatedSupplier() throws Exception {
        SupplierDto createdSupplier = new SupplierDto();
        when(supplierService.createSupplier(any(Supplier.class))).thenReturn(createdSupplier);

        mockMvc.perform(post("/api/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).createSupplier(any(Supplier.class));
    }

    @Test
    void createSupplier_ShouldReturnBadRequest() throws Exception {
        when(supplierService.createSupplier(any(Supplier.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(supplierService, times(1)).createSupplier(any(Supplier.class));
    }

    @Test
    void updateSupplier_ShouldReturnUpdatedSupplier() throws Exception {
        SupplierDto updatedSupplier = new SupplierDto();
        when(supplierService.updateSupplier(eq(1L), any(Supplier.class))).thenReturn(updatedSupplier);

        mockMvc.perform(put("/api/suppliers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).updateSupplier(eq(1L), any(Supplier.class));
    }

    @Test
    void updateSupplier_ShouldReturnBadRequest() throws Exception {
        when(supplierService.updateSupplier(eq(1L), any(Supplier.class))).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/suppliers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(supplierService, times(1)).updateSupplier(eq(1L), any(Supplier.class));
    }

    @Test
    void deleteSupplier_ShouldReturnNoContent() throws Exception {
        doNothing().when(supplierService).deleteSupplier(1L);

        mockMvc.perform(delete("/api/suppliers/1"))
                .andExpect(status().isNoContent());

        verify(supplierService, times(1)).deleteSupplier(1L);
    }

    @Test
    void deleteSupplier_ShouldReturnNotFound() throws Exception {
        doThrow(new RuntimeException()).when(supplierService).deleteSupplier(1L);

        mockMvc.perform(delete("/api/suppliers/1"))
                .andExpect(status().isNotFound());

        verify(supplierService, times(1)).deleteSupplier(1L);
    }

    @Test
    void getActiveSuppliers_ShouldReturnList() throws Exception {
        List<SupplierDto> suppliers = Arrays.asList(new SupplierDto());
        when(supplierService.getActiveSuppliers()).thenReturn(suppliers);

        mockMvc.perform(get("/api/suppliers/active"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).getActiveSuppliers();
    }

    @Test
    void getSuppliersByCategory_ShouldReturnList() throws Exception {
        List<SupplierDto> suppliers = Arrays.asList(new SupplierDto());
        when(supplierService.getSuppliersByCategory("Food")).thenReturn(suppliers);

        mockMvc.perform(get("/api/suppliers/category/Food"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).getSuppliersByCategory("Food");
    }

    @Test
    void getSuppliersByCity_ShouldReturnList() throws Exception {
        List<SupplierDto> suppliers = Arrays.asList(new SupplierDto());
        when(supplierService.getSuppliersByCity("Bogota")).thenReturn(suppliers);

        mockMvc.perform(get("/api/suppliers/city/Bogota"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).getSuppliersByCity("Bogota");
    }

    @Test
    void getSuppliersByCountry_ShouldReturnList() throws Exception {
        List<SupplierDto> suppliers = Arrays.asList(new SupplierDto());
        when(supplierService.getSuppliersByCountry("Colombia")).thenReturn(suppliers);

        mockMvc.perform(get("/api/suppliers/country/Colombia"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).getSuppliersByCountry("Colombia");
    }

    @Test
    void searchSuppliersByName_ShouldReturnList() throws Exception {
        List<SupplierDto> suppliers = Arrays.asList(new SupplierDto());
        when(supplierService.searchSuppliersByName("Supplier")).thenReturn(suppliers);

        mockMvc.perform(get("/api/suppliers/search")
                .param("name", "Supplier"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).searchSuppliersByName("Supplier");
    }

    @Test
    void getSuppliersByMinimumRating_ShouldReturnList() throws Exception {
        List<SupplierDto> suppliers = Arrays.asList(new SupplierDto());
        when(supplierService.getSuppliersByMinimumRating(4)).thenReturn(suppliers);

        mockMvc.perform(get("/api/suppliers/rating/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).getSuppliersByMinimumRating(4);
    }

    @Test
    void activateSupplier_ShouldReturnActivatedSupplier() throws Exception {
        SupplierDto activatedSupplier = new SupplierDto();
        when(supplierService.activateSupplier(1L)).thenReturn(activatedSupplier);

        mockMvc.perform(put("/api/suppliers/1/activate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).activateSupplier(1L);
    }

    @Test
    void activateSupplier_ShouldReturnBadRequest() throws Exception {
        when(supplierService.activateSupplier(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/suppliers/1/activate"))
                .andExpect(status().isBadRequest());

        verify(supplierService, times(1)).activateSupplier(1L);
    }

    @Test
    void deactivateSupplier_ShouldReturnDeactivatedSupplier() throws Exception {
        SupplierDto deactivatedSupplier = new SupplierDto();
        when(supplierService.deactivateSupplier(1L)).thenReturn(deactivatedSupplier);

        mockMvc.perform(put("/api/suppliers/1/deactivate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).deactivateSupplier(1L);
    }

    @Test
    void deactivateSupplier_ShouldReturnBadRequest() throws Exception {
        when(supplierService.deactivateSupplier(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/suppliers/1/deactivate"))
                .andExpect(status().isBadRequest());

        verify(supplierService, times(1)).deactivateSupplier(1L);
    }

    @Test
    void updateSupplierRating_ShouldReturnUpdatedSupplier() throws Exception {
        SupplierDto updatedSupplier = new SupplierDto();
        when(supplierService.updateSupplierRating(1L, 5)).thenReturn(updatedSupplier);

        mockMvc.perform(put("/api/suppliers/1/rating")
                .param("rating", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(supplierService, times(1)).updateSupplierRating(1L, 5);
    }

    @Test
    void updateSupplierRating_ShouldReturnBadRequest() throws Exception {
        when(supplierService.updateSupplierRating(1L, 5)).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/suppliers/1/rating")
                .param("rating", "5"))
                .andExpect(status().isBadRequest());

        verify(supplierService, times(1)).updateSupplierRating(1L, 5);
    }
}