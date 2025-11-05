package com.Trabajo.WorkSena.Tables.Controller;

import com.Trabajo.WorkSena.Tables.DTO.TableDto;
import com.Trabajo.WorkSena.Tables.Entity.DiningTable;
import com.Trabajo.WorkSena.Tables.Service.ITableService;
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

class TableControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ITableService tableService;

    @InjectMocks
    private TableController tableController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tableController).build();
    }

    @Test
    void getAllTables_ShouldReturnList() throws Exception {
        List<TableDto> tables = Arrays.asList(new TableDto(), new TableDto());
        when(tableService.getAllTables()).thenReturn(tables);

        mockMvc.perform(get("/api/tables"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).getAllTables();
    }

    @Test
    void getTableById_ShouldReturnTable() throws Exception {
        TableDto table = new TableDto();
        when(tableService.getTableById(1L)).thenReturn(java.util.Optional.of(table));

        mockMvc.perform(get("/api/tables/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).getTableById(1L);
    }

    @Test
    void getTableById_ShouldReturnNotFound() throws Exception {
        when(tableService.getTableById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/tables/1"))
                .andExpect(status().isNotFound());

        verify(tableService, times(1)).getTableById(1L);
    }

    @Test
    void getTableByNumber_ShouldReturnTable() throws Exception {
        TableDto table = new TableDto();
        when(tableService.getTableByNumber("T001")).thenReturn(java.util.Optional.of(table));

        mockMvc.perform(get("/api/tables/number/T001"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).getTableByNumber("T001");
    }

    @Test
    void getTableByNumber_ShouldReturnNotFound() throws Exception {
        when(tableService.getTableByNumber("T001")).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/tables/number/T001"))
                .andExpect(status().isNotFound());

        verify(tableService, times(1)).getTableByNumber("T001");
    }

    @Test
    void createTable_ShouldReturnCreatedTable() throws Exception {
        TableDto createdTable = new TableDto();
        when(tableService.createTable(any(DiningTable.class))).thenReturn(createdTable);

        mockMvc.perform(post("/api/tables")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tableNumber\":\"T001\",\"capacity\":4,\"status\":\"AVAILABLE\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).createTable(any(DiningTable.class));
    }

    @Test
    void createTable_ShouldReturnBadRequest() throws Exception {
        when(tableService.createTable(any(DiningTable.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/tables")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tableNumber\":\"T001\",\"capacity\":4,\"status\":\"AVAILABLE\"}"))
                .andExpect(status().isBadRequest());

        verify(tableService, times(1)).createTable(any(DiningTable.class));
    }

    @Test
    void updateTable_ShouldReturnUpdatedTable() throws Exception {
        TableDto updatedTable = new TableDto();
        when(tableService.updateTable(eq(1L), any(DiningTable.class))).thenReturn(updatedTable);

        mockMvc.perform(put("/api/tables/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).updateTable(eq(1L), any(DiningTable.class));
    }

    @Test
    void updateTable_ShouldReturnBadRequest() throws Exception {
        when(tableService.updateTable(eq(1L), any(DiningTable.class))).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/tables/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(tableService, times(1)).updateTable(eq(1L), any(DiningTable.class));
    }

    @Test
    void deleteTable_ShouldReturnNoContent() throws Exception {
        doNothing().when(tableService).deleteTable(1L);

        mockMvc.perform(delete("/api/tables/1"))
                .andExpect(status().isNoContent());

        verify(tableService, times(1)).deleteTable(1L);
    }

    @Test
    void deleteTable_ShouldReturnNotFound() throws Exception {
        doThrow(new RuntimeException()).when(tableService).deleteTable(1L);

        mockMvc.perform(delete("/api/tables/1"))
                .andExpect(status().isNotFound());

        verify(tableService, times(1)).deleteTable(1L);
    }

    @Test
    void updateTableStatus_ShouldReturnUpdatedTable() throws Exception {
        TableDto updatedTable = new TableDto();
        when(tableService.updateTableStatus(1L, DiningTable.TableStatus.OCCUPIED)).thenReturn(updatedTable);

        mockMvc.perform(put("/api/tables/1/status")
                .param("status", "OCCUPIED"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).updateTableStatus(1L, DiningTable.TableStatus.OCCUPIED);
    }

    @Test
    void updateTableStatus_ShouldReturnBadRequest() throws Exception {
        when(tableService.updateTableStatus(1L, DiningTable.TableStatus.OCCUPIED)).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/tables/1/status")
                .param("status", "OCCUPIED"))
                .andExpect(status().isBadRequest());

        verify(tableService, times(1)).updateTableStatus(1L, DiningTable.TableStatus.OCCUPIED);
    }

    @Test
    void occupyTable_ShouldReturnOccupiedTable() throws Exception {
        TableDto occupiedTable = new TableDto();
        when(tableService.occupyTable(1L)).thenReturn(occupiedTable);

        mockMvc.perform(put("/api/tables/1/occupy"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).occupyTable(1L);
    }

    @Test
    void occupyTable_ShouldReturnBadRequest() throws Exception {
        when(tableService.occupyTable(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/tables/1/occupy"))
                .andExpect(status().isBadRequest());

        verify(tableService, times(1)).occupyTable(1L);
    }

    @Test
    void freeTable_ShouldReturnFreedTable() throws Exception {
        TableDto freedTable = new TableDto();
        when(tableService.freeTable(1L)).thenReturn(freedTable);

        mockMvc.perform(put("/api/tables/1/free"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).freeTable(1L);
    }

    @Test
    void freeTable_ShouldReturnBadRequest() throws Exception {
        when(tableService.freeTable(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/tables/1/free"))
                .andExpect(status().isBadRequest());

        verify(tableService, times(1)).freeTable(1L);
    }

    @Test
    void reserveTable_ShouldReturnReservedTable() throws Exception {
        TableDto reservedTable = new TableDto();
        when(tableService.reserveTable(1L)).thenReturn(reservedTable);

        mockMvc.perform(put("/api/tables/1/reserve"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).reserveTable(1L);
    }

    @Test
    void reserveTable_ShouldReturnBadRequest() throws Exception {
        when(tableService.reserveTable(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/tables/1/reserve"))
                .andExpect(status().isBadRequest());

        verify(tableService, times(1)).reserveTable(1L);
    }

    @Test
    void markForMaintenance_ShouldReturnMaintenanceTable() throws Exception {
        TableDto maintenanceTable = new TableDto();
        when(tableService.markForMaintenance(1L)).thenReturn(maintenanceTable);

        mockMvc.perform(put("/api/tables/1/maintenance"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).markForMaintenance(1L);
    }

    @Test
    void markForMaintenance_ShouldReturnBadRequest() throws Exception {
        when(tableService.markForMaintenance(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/tables/1/maintenance"))
                .andExpect(status().isBadRequest());

        verify(tableService, times(1)).markForMaintenance(1L);
    }

    @Test
    void getTablesByStatus_ShouldReturnList() throws Exception {
        List<TableDto> tables = Arrays.asList(new TableDto());
        when(tableService.getTablesByStatus(DiningTable.TableStatus.AVAILABLE)).thenReturn(tables);

        mockMvc.perform(get("/api/tables/status/AVAILABLE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).getTablesByStatus(DiningTable.TableStatus.AVAILABLE);
    }

    @Test
    void getActiveTables_ShouldReturnList() throws Exception {
        List<TableDto> tables = Arrays.asList(new TableDto());
        when(tableService.getActiveTables()).thenReturn(tables);

        mockMvc.perform(get("/api/tables/active"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).getActiveTables();
    }

    @Test
    void getTablesByCapacity_ShouldReturnList() throws Exception {
        List<TableDto> tables = Arrays.asList(new TableDto());
        when(tableService.getTablesByCapacity(4)).thenReturn(tables);

        mockMvc.perform(get("/api/tables/capacity/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).getTablesByCapacity(4);
    }

    @Test
    void getTablesByLocation_ShouldReturnList() throws Exception {
        List<TableDto> tables = Arrays.asList(new TableDto());
        when(tableService.getTablesByLocation("Patio")).thenReturn(tables);

        mockMvc.perform(get("/api/tables/location/Patio"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(tableService, times(1)).getTablesByLocation("Patio");
    }
}