package com.Trabajo.WorkSena.Suppliers.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Trabajo.WorkSena.Suppliers.DTO.SupplierDto;
import com.Trabajo.WorkSena.Suppliers.Entity.Supplier;
import com.Trabajo.WorkSena.Suppliers.Service.ISupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "*")
@Tag(name = "Suppliers", description = "API para gestión de proveedores del restaurante")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        List<SupplierDto> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id)
            .map(supplier -> ResponseEntity.ok(supplier))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SupplierDto> getSupplierByName(@PathVariable String name) {
        return supplierService.getSupplierByName(name)
            .map(supplier -> ResponseEntity.ok(supplier))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo proveedor", description = "Crea un nuevo proveedor en el restaurante con la información proporcionada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o proveedor ya existe",
            content = @Content)
    })
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto) {
        try {
            // Convertir DTO a entidad
            Supplier supplier = new Supplier();
            supplier.setName(supplierDto.getName());
            supplier.setContactPerson(supplierDto.getContactPerson());
            supplier.setPhone(supplierDto.getPhone());
            supplier.setEmail(supplierDto.getEmail());
            supplier.setAddress(supplierDto.getAddress());
            supplier.setCity(supplierDto.getCity());
            supplier.setCountry(supplierDto.getCountry());
            supplier.setTaxId(supplierDto.getTaxId());
            supplier.setPaymentTerms(supplierDto.getPaymentTerms());
            supplier.setSupplierCategory(supplierDto.getSupplierCategory());
            supplier.setIsActive(supplierDto.getIsActive() != null ? supplierDto.getIsActive() : true);
            supplier.setRating(supplierDto.getRating());
            supplier.setNotes(supplierDto.getNotes());

            SupplierDto createdSupplier = supplierService.createSupplier(supplier);
            return ResponseEntity.ok(createdSupplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proveedor", description = "Actualiza la información de un proveedor existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor actualizado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content)
    })
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long id, @RequestBody SupplierDto supplierDto) {
        try {
            // Convertir DTO a entidad
            Supplier supplier = new Supplier();
            supplier.setName(supplierDto.getName());
            supplier.setContactPerson(supplierDto.getContactPerson());
            supplier.setPhone(supplierDto.getPhone());
            supplier.setEmail(supplierDto.getEmail());
            supplier.setAddress(supplierDto.getAddress());
            supplier.setCity(supplierDto.getCity());
            supplier.setCountry(supplierDto.getCountry());
            supplier.setTaxId(supplierDto.getTaxId());
            supplier.setPaymentTerms(supplierDto.getPaymentTerms());
            supplier.setSupplierCategory(supplierDto.getSupplierCategory());
            supplier.setIsActive(supplierDto.getIsActive());
            supplier.setRating(supplierDto.getRating());
            supplier.setNotes(supplierDto.getNotes());

            SupplierDto updatedSupplier = supplierService.updateSupplier(id, supplier);
            return ResponseEntity.ok(updatedSupplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        try {
            supplierService.deleteSupplier(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Filtering endpoints
    @GetMapping("/active")
    public ResponseEntity<List<SupplierDto>> getActiveSuppliers() {
        List<SupplierDto> suppliers = supplierService.getActiveSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<SupplierDto>> getSuppliersByCategory(@PathVariable String category) {
        List<SupplierDto> suppliers = supplierService.getSuppliersByCategory(category);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<SupplierDto>> getSuppliersByCity(@PathVariable String city) {
        List<SupplierDto> suppliers = supplierService.getSuppliersByCity(city);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<SupplierDto>> getSuppliersByCountry(@PathVariable String country) {
        List<SupplierDto> suppliers = supplierService.getSuppliersByCountry(country);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SupplierDto>> searchSuppliersByName(@RequestParam String name) {
        List<SupplierDto> suppliers = supplierService.searchSuppliersByName(name);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/rating/{minRating}")
    public ResponseEntity<List<SupplierDto>> getSuppliersByMinimumRating(@PathVariable Integer minRating) {
        List<SupplierDto> suppliers = supplierService.getSuppliersByMinimumRating(minRating);
        return ResponseEntity.ok(suppliers);
    }

    // Business operations endpoints
    @PutMapping("/{id}/activate")
    public ResponseEntity<SupplierDto> activateSupplier(@PathVariable Long id) {
        try {
            SupplierDto activatedSupplier = supplierService.activateSupplier(id);
            return ResponseEntity.ok(activatedSupplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<SupplierDto> deactivateSupplier(@PathVariable Long id) {
        try {
            SupplierDto deactivatedSupplier = supplierService.deactivateSupplier(id);
            return ResponseEntity.ok(deactivatedSupplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/rating")
    public ResponseEntity<SupplierDto> updateSupplierRating(@PathVariable Long id, @RequestParam Integer rating) {
        try {
            SupplierDto updatedSupplier = supplierService.updateSupplierRating(id, rating);
            return ResponseEntity.ok(updatedSupplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
