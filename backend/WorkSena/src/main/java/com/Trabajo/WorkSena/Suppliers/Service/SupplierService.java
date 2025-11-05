package com.Trabajo.WorkSena.Suppliers.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trabajo.WorkSena.Suppliers.DTO.SupplierDto;
import com.Trabajo.WorkSena.Suppliers.Entity.Supplier;
import com.Trabajo.WorkSena.Suppliers.Repository.ISupplierRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public List<SupplierDto> getAllSuppliers() {
        return supplierRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierDto> getSupplierById(Long id) {
        return supplierRepository.findById(id)
            .map(this::convertToDto);
    }

    @Override
    public SupplierDto createSupplier(Supplier supplier) {
        if (supplierRepository.existsByName(supplier.getName())) {
            throw new RuntimeException("Supplier name already exists");
        }
        Supplier savedSupplier = supplierRepository.save(supplier);
        return convertToDto(savedSupplier);
    }

    @Override
    public SupplierDto updateSupplier(Long id, Supplier supplierDetails) {
        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found"));

        // Check if name is being changed and if it already exists
        if (!supplier.getName().equals(supplierDetails.getName()) &&
            supplierRepository.existsByName(supplierDetails.getName())) {
            throw new RuntimeException("Supplier name already exists");
        }

        supplier.setName(supplierDetails.getName());
        supplier.setContactPerson(supplierDetails.getContactPerson());
        supplier.setPhone(supplierDetails.getPhone());
        supplier.setEmail(supplierDetails.getEmail());
        supplier.setAddress(supplierDetails.getAddress());
        supplier.setCity(supplierDetails.getCity());
        supplier.setCountry(supplierDetails.getCountry());
        supplier.setTaxId(supplierDetails.getTaxId());
        supplier.setPaymentTerms(supplierDetails.getPaymentTerms());
        supplier.setSupplierCategory(supplierDetails.getSupplierCategory());
        supplier.setIsActive(supplierDetails.getIsActive());
        supplier.setRating(supplierDetails.getRating());
        supplier.setNotes(supplierDetails.getNotes());

        Supplier updatedSupplier = supplierRepository.save(supplier);
        return convertToDto(updatedSupplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplierRepository.delete(supplier);
    }

    @Override
    public List<SupplierDto> getActiveSuppliers() {
        return supplierRepository.findByIsActiveTrue().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<SupplierDto> getSuppliersByCategory(String category) {
        return supplierRepository.findBySupplierCategory(category).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<SupplierDto> getSuppliersByCity(String city) {
        return supplierRepository.findByCity(city).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<SupplierDto> getSuppliersByCountry(String country) {
        return supplierRepository.findByCountry(country).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<SupplierDto> searchSuppliersByName(String name) {
        return supplierRepository.findByNameContainingIgnoreCase(name).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<SupplierDto> getSuppliersByMinimumRating(Integer minRating) {
        return supplierRepository.findByMinimumRating(minRating).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierDto> getSupplierByName(String name) {
        return supplierRepository.findByName(name)
            .map(this::convertToDto);
    }

    @Override
    public SupplierDto activateSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplier.setIsActive(true);
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return convertToDto(updatedSupplier);
    }

    @Override
    public SupplierDto deactivateSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplier.setIsActive(false);
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return convertToDto(updatedSupplier);
    }

    @Override
    public SupplierDto updateSupplierRating(Long id, Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplier.setRating(rating);
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return convertToDto(updatedSupplier);
    }

    private SupplierDto convertToDto(Supplier supplier) {
        SupplierDto dto = new SupplierDto();
        dto.setName(supplier.getName());
        dto.setContactPerson(supplier.getContactPerson());
        dto.setPhone(supplier.getPhone());
        dto.setEmail(supplier.getEmail());
        dto.setAddress(supplier.getAddress());
        dto.setCity(supplier.getCity());
        dto.setCountry(supplier.getCountry());
        dto.setTaxId(supplier.getTaxId());
        dto.setPaymentTerms(supplier.getPaymentTerms());
        dto.setSupplierCategory(supplier.getSupplierCategory());
        dto.setIsActive(supplier.getIsActive());
        dto.setRating(supplier.getRating());
        dto.setNotes(supplier.getNotes());
        return dto;
    }
}
