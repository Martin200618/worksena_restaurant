package com.Trabajo.WorkSena.Tables.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Trabajo.WorkSena.Tables.DTO.TableDto;
import com.Trabajo.WorkSena.Tables.Entity.DiningTable;
import com.Trabajo.WorkSena.Tables.Service.ITableService;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin(origins = "*")
public class TableController {

    @Autowired
    private ITableService tableService;

    @GetMapping
    public ResponseEntity<List<TableDto>> getAllTables() {
        List<TableDto> tables = tableService.getAllTables();
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableDto> getTableById(@PathVariable Long id) {
        return tableService.getTableById(id)
            .map(table -> ResponseEntity.ok(table))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{tableNumber}")
    public ResponseEntity<TableDto> getTableByNumber(@PathVariable String tableNumber) {
        return tableService.getTableByNumber(tableNumber)
            .map(table -> ResponseEntity.ok(table))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TableDto> createTable(@RequestBody TableDto tableDto) {
        System.out.println("=== CREATE TABLE REQUEST RECEIVED ===");
        System.out.println("TableDto received: " + tableDto);

        if (tableDto == null) {
            System.out.println("ERROR: tableDto is null");
            return ResponseEntity.badRequest().build();
        }

        try {
            System.out.println("Table number: " + tableDto.getTableNumber());
            System.out.println("Capacity: " + tableDto.getCapacity());
            System.out.println("Status: " + tableDto.getStatus());

            // Validaciones básicas
            if (tableDto.getTableNumber() == null || tableDto.getTableNumber().trim().isEmpty()) {
                System.out.println("ERROR: tableNumber is null or empty");
                return ResponseEntity.badRequest().build();
            }

            if (tableDto.getCapacity() == null || tableDto.getCapacity() <= 0) {
                System.out.println("ERROR: capacity is null or <= 0");
                return ResponseEntity.badRequest().build();
            }

            if (tableDto.getStatus() == null || tableDto.getStatus().trim().isEmpty()) {
                System.out.println("ERROR: status is null or empty");
                return ResponseEntity.badRequest().build();
            }

            // Convertir DTO a entidad
            DiningTable table = new DiningTable();
            table.setTableNumber(tableDto.getTableNumber().trim());
            table.setCapacity(tableDto.getCapacity());
            table.setLocation(tableDto.getLocation() != null ? tableDto.getLocation().trim() : null);
            table.setDescription(tableDto.getDescription() != null ? tableDto.getDescription().trim() : null);
            table.setIsActive(tableDto.getIsActive() != null ? tableDto.getIsActive() : true);

            // Convertir status string a enum
            try {
                DiningTable.TableStatus statusEnum = DiningTable.TableStatus.valueOf(tableDto.getStatus().toUpperCase().trim());
                table.setStatus(statusEnum);
                System.out.println("Status converted to enum: " + statusEnum);
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Invalid status '" + tableDto.getStatus() + "'. Valid values: AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE, OUT_OF_SERVICE");
                return ResponseEntity.badRequest().build();
            }

            System.out.println("Calling tableService.createTable...");
            TableDto createdTable = tableService.createTable(table);
            System.out.println("SUCCESS: Table created with ID: " + createdTable.getId());
            return ResponseEntity.ok(createdTable);

        } catch (Exception e) {
            System.out.println("ERROR: Exception occurred: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableDto> updateTable(@PathVariable Long id, @RequestBody DiningTable tableDetails) {
        try {
            TableDto updatedTable = tableService.updateTable(id, tableDetails);
            return ResponseEntity.ok(updatedTable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        try {
            tableService.deleteTable(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Status management endpoints
    @PutMapping("/{id}/status")
    public ResponseEntity<TableDto> updateTableStatus(@PathVariable Long id, @RequestParam DiningTable.TableStatus status) {
        try {
            TableDto updatedTable = tableService.updateTableStatus(id, status);
            return ResponseEntity.ok(updatedTable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/occupy")
    public ResponseEntity<TableDto> occupyTable(@PathVariable Long id) {
        try {
            TableDto occupiedTable = tableService.occupyTable(id);
            return ResponseEntity.ok(occupiedTable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/free")
    public ResponseEntity<TableDto> freeTable(@PathVariable Long id) {
        try {
            TableDto freedTable = tableService.freeTable(id);
            return ResponseEntity.ok(freedTable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<TableDto> reserveTable(@PathVariable Long id) {
        try {
            TableDto reservedTable = tableService.reserveTable(id);
            return ResponseEntity.ok(reservedTable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/maintenance")
    public ResponseEntity<TableDto> markForMaintenance(@PathVariable Long id) {
        try {
            TableDto maintenanceTable = tableService.markForMaintenance(id);
            return ResponseEntity.ok(maintenanceTable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Filtering endpoints
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TableDto>> getTablesByStatus(@PathVariable DiningTable.TableStatus status) {
        List<TableDto> tables = tableService.getTablesByStatus(status);
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/active")
    public ResponseEntity<List<TableDto>> getActiveTables() {
        List<TableDto> tables = tableService.getActiveTables();
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/capacity/{minCapacity}")
    public ResponseEntity<List<TableDto>> getTablesByCapacity(@PathVariable Integer minCapacity) {
        List<TableDto> tables = tableService.getTablesByCapacity(minCapacity);
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<TableDto>> getTablesByLocation(@PathVariable String location) {
        List<TableDto> tables = tableService.getTablesByLocation(location);
        return ResponseEntity.ok(tables);
    }
}
