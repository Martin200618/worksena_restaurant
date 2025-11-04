package com.Trabajo.WorkSena.Tables.Service;

import java.util.List;
import java.util.Optional;

import com.Trabajo.WorkSena.Tables.DTO.TableDto;
import com.Trabajo.WorkSena.Tables.Entity.DiningTable;

public interface ITableService {
    List<TableDto> getAllTables();
    Optional<TableDto> getTableById(Long id);
    TableDto createTable(DiningTable table);
    TableDto updateTable(Long id, DiningTable tableDetails);
    void deleteTable(Long id);

    // Status management
    TableDto updateTableStatus(Long id, DiningTable.TableStatus status);

    // Filtering operations
    List<TableDto> getTablesByStatus(DiningTable.TableStatus status);
    List<TableDto> getActiveTables();
    List<TableDto> getTablesByCapacity(Integer minCapacity);
    List<TableDto> getTablesByLocation(String location);
    Optional<TableDto> getTableByNumber(String tableNumber);

    // Business operations
    TableDto occupyTable(Long id);
    TableDto freeTable(Long id);
    TableDto reserveTable(Long id);
    TableDto markForMaintenance(Long id);
}
