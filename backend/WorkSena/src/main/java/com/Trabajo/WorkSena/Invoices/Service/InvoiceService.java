package com.Trabajo.WorkSena.Invoices.Service;

import com.Trabajo.WorkSena.Invoices.DTO.InvoiceDto;
import com.Trabajo.WorkSena.Invoices.DTO.InvoiceItemDto;
import com.Trabajo.WorkSena.Invoices.Entity.Invoice;
import com.Trabajo.WorkSena.Invoices.Entity.InvoiceItem;
import com.Trabajo.WorkSena.Invoices.Repository.IInvoiceRepository;
import com.Trabajo.WorkSena.Invoices.Repository.IInvoiceItemRepository;
import com.Trabajo.WorkSena.Orders.Entity.Order;
import com.Trabajo.WorkSena.Orders.Entity.OrderMenuItem;
import com.Trabajo.WorkSena.Orders.Service.IOrderService;
import com.Trabajo.WorkSena.Orders.DTO.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IInvoiceItemRepository invoiceItemRepository;

    @Autowired
    private IOrderService orderService;

    @Override
    public List<InvoiceDto> getAllInvoices() {
        return invoiceRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<InvoiceDto> getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
            .map(this::convertToDto);
    }

    @Override
    public InvoiceDto getInvoiceByNumber(String invoiceNumber) {
        Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber);
        return invoice != null ? convertToDto(invoice) : null;
    }

    @Override
    public List<InvoiceDto> getInvoicesByOrderId(Long orderId) {
        return invoiceRepository.findByOrderId(orderId).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto createInvoiceFromOrder(Long orderId) {
        // Get the order
        Optional<OrderDto> orderDtoOpt = orderService.getOrderById(orderId);
        if (orderDtoOpt.isEmpty()) {
            throw new RuntimeException("Order not found");
        }

        OrderDto orderDto = orderDtoOpt.get();

        // Convert OrderDto to Order entity (simplified approach)
        Order order = new Order();
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setTableId(orderDto.getTableId());
        order.setCustomerName(orderDto.getCustomerName());
        order.setCustomerPhone(orderDto.getCustomerPhone());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setNotes(orderDto.getNotes());

        // Check if invoice already exists for this order
        List<Invoice> existingInvoices = invoiceRepository.findByOrderId(orderId);
        if (!existingInvoices.isEmpty()) {
            throw new RuntimeException("Invoice already exists for this order");
        }

        // Create invoice
        Invoice invoice = new Invoice();
        invoice.setOrderId(orderId);
        invoice.setCustomerName(order.getCustomerName());
        invoice.setCustomerPhone(order.getCustomerPhone());
        invoice.setTableNumber(order.getTableId() != null ? order.getTableId().intValue() : null);
        invoice.setSubtotal(order.getTotalAmount());
        invoice.setNotes("Factura generada desde pedido #" + order.getOrderNumber());

        // Save invoice first to get ID
        Invoice savedInvoice = invoiceRepository.save(invoice);

        // Create invoice items from order items
        if (order.getOrderMenuItems() != null) {
            for (OrderMenuItem orderItem : order.getOrderMenuItems()) {
                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setInvoice(savedInvoice);
                invoiceItem.setMenuItemName(orderItem.getMenuItemName());
                invoiceItem.setQuantity(orderItem.getQuantity());
                invoiceItem.setUnitPrice(orderItem.getUnitPrice());
                invoiceItem.setNotes(orderItem.getNotes());

                invoiceItemRepository.save(invoiceItem);
            }
        }

        return convertToDto(savedInvoice);
    }

    @Override
    public InvoiceDto updateInvoiceStatus(Long id, String status) {
        Invoice invoice = invoiceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Invoice not found"));

        try {
            Invoice.InvoiceStatus invoiceStatus = Invoice.InvoiceStatus.valueOf(status.toUpperCase());
            invoice.setStatus(invoiceStatus);
            Invoice updatedInvoice = invoiceRepository.save(invoice);
            return convertToDto(updatedInvoice);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid invoice status: " + status);
        }
    }

    @Override
    public void deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // Delete associated invoice items first
        List<InvoiceItem> items = invoiceItemRepository.findByInvoiceId(id);
        for (InvoiceItem item : items) {
            invoiceItemRepository.delete(item);
        }

        invoiceRepository.delete(invoice);
    }

    @Override
    public List<InvoiceDto> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return invoiceRepository.findByDateRange(startDate, endDate).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalRevenueByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        BigDecimal total = invoiceRepository.getTotalRevenueByDateRange(startDate, endDate);
        return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    public Long countPaidInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        Long count = invoiceRepository.countPaidInvoicesByDateRange(startDate, endDate);
        return count != null ? count : 0L;
    }

    private InvoiceDto convertToDto(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setOrderId(invoice.getOrderId());
        dto.setCustomerName(invoice.getCustomerName());
        dto.setCustomerPhone(invoice.getCustomerPhone());
        dto.setTableNumber(invoice.getTableNumber());
        dto.setSubtotal(invoice.getSubtotal());
        dto.setTaxRate(invoice.getTaxRate());
        dto.setTaxAmount(invoice.getTaxAmount());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setStatus(invoice.getStatus().toString());
        dto.setIssuedAt(invoice.getIssuedAt());
        dto.setDueDate(invoice.getDueDate());
        dto.setNotes(invoice.getNotes());

        // Load invoice items
        List<InvoiceItem> items = invoiceItemRepository.findByInvoiceId(invoice.getId());
        List<InvoiceItemDto> itemDtos = items.stream()
            .map(this::convertItemToDto)
            .collect(Collectors.toList());
        dto.setItems(itemDtos);

        return dto;
    }

    private InvoiceItemDto convertItemToDto(InvoiceItem item) {
        InvoiceItemDto dto = new InvoiceItemDto();
        dto.setId(item.getId());
        dto.setInvoiceId(item.getInvoice().getId());
        dto.setMenuItemName(item.getMenuItemName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setTotalPrice(item.getTotalPrice());
        dto.setNotes(item.getNotes());
        return dto;
    }
}
