package com.Trabajo.WorkSena.Tables.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Trabajo.WorkSena.Tables.Entity.DiningTable;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITableRepository extends JpaRepository<DiningTable, Long> {
    Optional<DiningTable> findByTableNumber(String tableNumber);
    List<DiningTable> findByStatus(DiningTable.TableStatus status);
    List<DiningTable> findByIsActiveTrue();
    List<DiningTable> findByCapacityGreaterThanEqual(Integer capacity);
    List<DiningTable> findByLocation(String location);
    boolean existsByTableNumber(String tableNumber);
}
