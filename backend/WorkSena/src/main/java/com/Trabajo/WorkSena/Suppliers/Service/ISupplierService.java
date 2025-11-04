package com.Trabajo.WorkSena.Suppliers.Service;

import java.util.List;
import java.util.Optional;

import com.Trabajo.WorkSena.Suppliers.DTO.SupplierDto;
import com.Trabajo.WorkSena.Suppliers.Entity.Supplier;

public interface ISupplierService {
    List<SupplierDto> getAllSuppliers();
    Optional<SupplierDto> getSupplierById(Long id);
    SupplierDto createSupplier(Supplier supplier);
    SupplierDto updateSupplier(Long id, Supplier supplierDetails);
    void deleteSupplier(Long id);

    // Filtering operations
    List<SupplierDto> getActiveSuppliers();
    List<SupplierDto> getSuppliersByCategory(String category);
    List<SupplierDto> getSuppliersByCity(String city);
    List<SupplierDto> getSuppliersByCountry(String country);
    List<SupplierDto> searchSuppliersByName(String name);
    List<SupplierDto> getSuppliersByMinimumRating(Integer minRating);
    Optional<SupplierDto> getSupplierByName(String name);

    // Business operations
    SupplierDto activateSupplier(Long id);
    SupplierDto deactivateSupplier(Long id);
    SupplierDto updateSupplierRating(Long id, Integer rating);
}
