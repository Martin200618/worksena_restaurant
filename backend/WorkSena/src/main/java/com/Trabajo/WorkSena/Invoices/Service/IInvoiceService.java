package com.Trabajo.WorkSena.Invoices.Service;

import com.Trabajo.WorkSena.Invoices.DTO.InvoiceDto;
import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
    List<InvoiceDto> getAllInvoices();
    Optional<InvoiceDto> getInvoiceById(Long id);
    InvoiceDto getInvoiceByNumber(String invoiceNumber);
    List<InvoiceDto> getInvoicesByOrderId(Long orderId);
    InvoiceDto createInvoiceFromOrder(Long orderId);
    InvoiceDto updateInvoiceStatus(Long id, String status);
    void deleteInvoice(Long id);

    // Report methods
    List<InvoiceDto> getInvoicesByDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
    java.math.BigDecimal getTotalRevenueByDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
    Long countPaidInvoicesByDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
}
