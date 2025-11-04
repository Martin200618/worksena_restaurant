package com.Trabajo.WorkSena.Suppliers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Trabajo.WorkSena.Suppliers.Entity.Supplier;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByName(String name);
    List<Supplier> findByIsActiveTrue();
    List<Supplier> findBySupplierCategory(String category);
    List<Supplier> findByCity(String city);
    List<Supplier> findByCountry(String country);

    @Query("SELECT s FROM Supplier s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Supplier> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT s FROM Supplier s WHERE s.rating >= :minRating")
    List<Supplier> findByMinimumRating(@Param("minRating") Integer minRating);

    boolean existsByName(String name);
}
