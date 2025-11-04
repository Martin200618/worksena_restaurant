package com.Trabajo.WorkSena.Reports.Controller;

import com.Trabajo.WorkSena.Reports.Service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    void getSalesReport_ShouldReturnReport() throws Exception {
        Map<String, Object> report = new HashMap<>();
        report.put("totalSales", 1000.0);
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        when(reportService.getSalesReport(startDate, endDate)).thenReturn(report);

        mockMvc.perform(get("/api/reports/sales")
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(reportService, times(1)).getSalesReport(any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void getInventoryReport_ShouldReturnReport() throws Exception {
        Map<String, Object> report = new HashMap<>();
        report.put("totalItems", 50);
        when(reportService.getInventoryReport()).thenReturn(report);

        mockMvc.perform(get("/api/reports/inventory"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(reportService, times(1)).getInventoryReport();
    }

    @Test
    void getInvoiceReport_ShouldReturnReport() throws Exception {
        Map<String, Object> report = new HashMap<>();
        report.put("totalInvoices", 10);
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        when(reportService.getInvoiceReport(startDate, endDate)).thenReturn(report);

        mockMvc.perform(get("/api/reports/invoices")
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(reportService, times(1)).getInvoiceReport(any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void getDashboardSummary_ShouldReturnSummary() throws Exception {
        Map<String, Object> summary = new HashMap<>();
        summary.put("activeOrders", 5);
        when(reportService.getDashboardSummary()).thenReturn(summary);

        mockMvc.perform(get("/api/reports/dashboard"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(reportService, times(1)).getDashboardSummary();
    }
}