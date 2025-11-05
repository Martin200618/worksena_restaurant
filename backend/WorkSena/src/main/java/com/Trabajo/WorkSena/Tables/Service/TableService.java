package com.Trabajo.WorkSena.Tables.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trabajo.WorkSena.Tables.DTO.TableDto;
import com.Trabajo.WorkSena.Tables.Entity.DiningTable;
import com.Trabajo.WorkSena.Tables.Repository.ITableRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableService implements ITableService {

    @Autowired
    private ITableRepository tableRepository;

    @Override
    public List<TableDto> getAllTables() {
        return tableRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<TableDto> getTableById(Long id) {
        return tableRepository.findById(id)
            .map(this::convertToDto);
    }

    @Override
    public TableDto createTable(DiningTable table) {
        if (tableRepository.existsByTableNumber(table.getTableNumber())) {
            throw new RuntimeException("Table number already exists");
        }
        DiningTable savedTable = tableRepository.save(table);
        return convertToDto(savedTable);
    }

    @Override
    public TableDto updateTable(Long id, DiningTable tableDetails) {
        DiningTable table = tableRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Table not found"));

        // Check if table number is being changed and if it already exists
        if (!table.getTableNumber().equals(tableDetails.getTableNumber()) &&
            tableRepository.existsByTableNumber(tableDetails.getTableNumber())) {
            throw new RuntimeException("Table number already exists");
        }

        table.setTableNumber(tableDetails.getTableNumber());
        table.setCapacity(tableDetails.getCapacity());
        table.setLocation(tableDetails.getLocation());
        table.setDescription(tableDetails.getDescription());
        table.setIsActive(tableDetails.getIsActive());

        DiningTable updatedTable = tableRepository.save(table);
        return convertToDto(updatedTable);
    }

    @Override
    public void deleteTable(Long id) {
        DiningTable table = tableRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Table not found"));
        tableRepository.delete(table);
    }

    @Override
    public TableDto updateTableStatus(Long id, DiningTable.TableStatus status) {
        DiningTable table = tableRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Table not found"));

        table.setStatus(status);
        DiningTable updatedTable = tableRepository.save(table);
        return convertToDto(updatedTable);
    }

    @Override
    public List<TableDto> getTablesByStatus(DiningTable.TableStatus status) {
        return tableRepository.findByStatus(status).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<TableDto> getActiveTables() {
        return tableRepository.findByIsActiveTrue().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<TableDto> getTablesByCapacity(Integer minCapacity) {
        return tableRepository.findByCapacityGreaterThanEqual(minCapacity).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<TableDto> getTablesByLocation(String location) {
        return tableRepository.findByLocation(location).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<TableDto> getTableByNumber(String tableNumber) {
        return tableRepository.findByTableNumber(tableNumber)
            .map(this::convertToDto);
    }

    @Override
    public TableDto occupyTable(Long id) {
        return updateTableStatus(id, DiningTable.TableStatus.OCCUPIED);
    }

    @Override
    public TableDto freeTable(Long id) {
        return updateTableStatus(id, DiningTable.TableStatus.AVAILABLE);
    }

    @Override
    public TableDto reserveTable(Long id) {
        return updateTableStatus(id, DiningTable.TableStatus.RESERVED);
    }

    @Override
    public TableDto markForMaintenance(Long id) {
        return updateTableStatus(id, DiningTable.TableStatus.MAINTENANCE);
    }

    private TableDto convertToDto(DiningTable table) {
        TableDto dto = new TableDto();
        dto.setTableNumber(table.getTableNumber());
        dto.setCapacity(table.getCapacity());
        dto.setStatus(table.getStatus().toString());
        dto.setLocation(table.getLocation());
        dto.setDescription(table.getDescription());
        dto.setIsActive(table.getIsActive());
        return dto;
    }
}
