package com.Trabajo.WorkSena.Suppliers.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para representar un proveedor en el restaurante")
public class SupplierDto {
    @Schema(description = "Nombre del proveedor", example = "Proveedor ABC")
    private String name;

    @Schema(description = "Persona de contacto", example = "Juan Pérez")
    private String contactPerson;

    @Schema(description = "Teléfono de contacto", example = "+57 300 123 4567")
    private String phone;

    @Schema(description = "Correo electrónico", example = "contacto@proveedorabc.com")
    private String email;

    @Schema(description = "Dirección", example = "Calle 123 #45-67")
    private String address;

    @Schema(description = "Ciudad", example = "Bogotá")
    private String city;

    @Schema(description = "País", example = "Colombia")
    private String country;

    @Schema(description = "ID fiscal", example = "901234567-8")
    private String taxId;

    @Schema(description = "Términos de pago", example = "Net 30")
    private String paymentTerms;

    @Schema(description = "Categoría del proveedor", example = "Food")
    private String supplierCategory;

    @Schema(description = "Indica si el proveedor está activo", example = "true")
    private Boolean isActive;

    @Schema(description = "Calificación del proveedor (1-5)", example = "4")
    private Integer rating;

    @Schema(description = "Notas adicionales", example = "Proveedor confiable de alimentos frescos")
    private String notes;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getTaxId() { return taxId; }
    public void setTaxId(String taxId) { this.taxId = taxId; }

    public String getPaymentTerms() { return paymentTerms; }
    public void setPaymentTerms(String paymentTerms) { this.paymentTerms = paymentTerms; }

    public String getSupplierCategory() { return supplierCategory; }
    public void setSupplierCategory(String supplierCategory) { this.supplierCategory = supplierCategory; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
