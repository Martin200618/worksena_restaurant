package com.Trabajo.WorkSena.Reports.Service;

import com.Trabajo.WorkSena.Inventory.Repository.IInventoryRepository;
import com.Trabajo.WorkSena.Orders.Repository.IOrderRepository;
import com.Trabajo.WorkSena.Invoices.Repository.IInvoiceRepository;
import com.Trabajo.WorkSena.Invoices.Entity.Invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IInventoryRepository inventoryRepository;

    public Map<String, Object> getSalesReport(LocalDateTime startDate, LocalDateTime endDate) {
        Map<String, Object> report = new HashMap<>();

        // Total orders
        long totalOrders = orderRepository.findByDateRange(startDate, endDate).size();
        report.put("totalOrders", totalOrders);

        // Total revenue
        BigDecimal totalRevenue = invoiceRepository.getTotalRevenueByDateRange(startDate, endDate);
        if (totalRevenue == null) totalRevenue = BigDecimal.ZERO;
        report.put("totalRevenue", totalRevenue);

        // Orders by status
        Map<String, Long> ordersByStatus = new HashMap<>();
        for (var status : com.Trabajo.WorkSena.Orders.Entity.Order.OrderStatus.values()) {
            long count = orderRepository.findByStatusAndDateRange(status, startDate, endDate).size();
            ordersByStatus.put(status.toString(), count);
        }
        report.put("ordersByStatus", ordersByStatus);

        // Average order value
        BigDecimal avgOrderValue = totalOrders > 0 ? totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, BigDecimal.ROUND_HALF_UP) : BigDecimal.ZERO;
        report.put("averageOrderValue", avgOrderValue);

        return report;
    }

    public Map<String, Object> getInventoryReport() {
        Map<String, Object> report = new HashMap<>();

        // Total items
        long totalItems = inventoryRepository.findAll().size();
        report.put("totalItems", totalItems);

        // Low stock items
        long lowStockItems = inventoryRepository.findLowStockItems().size();
        report.put("lowStockItems", lowStockItems);

        // Out of stock items
        long outOfStockItems = inventoryRepository.findOutOfStockItems().size();
        report.put("outOfStockItems", outOfStockItems);

        // Items needing restock
        long itemsNeedingRestock = inventoryRepository.findItemsNeedingRestock().size();
        report.put("itemsNeedingRestock", itemsNeedingRestock);

        return report;
    }

    public Map<String, Object> getInvoiceReport(LocalDateTime startDate, LocalDateTime endDate) {
        Map<String, Object> report = new HashMap<>();

        // Total invoices
        long totalInvoices = invoiceRepository.findByDateRange(startDate, endDate).size();
        report.put("totalInvoices", totalInvoices);

        // Total amount
        BigDecimal totalAmount = invoiceRepository.getTotalRevenueByDateRange(startDate, endDate);
        if (totalAmount == null) totalAmount = BigDecimal.ZERO;
        report.put("totalAmount", totalAmount);

        // Invoices by status
        Map<String, Long> invoicesByStatus = new HashMap<>();
        for (var status : Invoice.InvoiceStatus.values()) {
            long count = invoiceRepository.findByStatus(status).stream()
                .filter(i -> i.getIssuedAt().isAfter(startDate) && i.getIssuedAt().isBefore(endDate))
                .count();
            invoicesByStatus.put(status.toString(), count);
        }
        report.put("invoicesByStatus", invoicesByStatus);

        // Paid invoices count
        Long paidInvoices = invoiceRepository.countPaidInvoicesByDateRange(startDate, endDate);
        report.put("paidInvoices", paidInvoices != null ? paidInvoices : 0L);

        return report;
    }

    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();

        LocalDateTime today = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime tomorrow = today.plusDays(1);

        // Today's sales
        BigDecimal todaySales = invoiceRepository.getTotalRevenueByDateRange(today, tomorrow);
        if (todaySales == null) todaySales = BigDecimal.ZERO;
        summary.put("todaySales", todaySales);

        // Today's orders
        long todayOrders = orderRepository.findByDateRange(today, tomorrow).size();
        summary.put("todayOrders", todayOrders);

        // Low stock alerts
        long lowStockAlerts = inventoryRepository.findLowStockItems().size();
        summary.put("lowStockAlerts", lowStockAlerts);

        // Pending invoices
        long pendingInvoices = invoiceRepository.findByStatus(Invoice.InvoiceStatus.PENDING).size();
        summary.put("pendingInvoices", pendingInvoices);

        return summary;
    }
}
