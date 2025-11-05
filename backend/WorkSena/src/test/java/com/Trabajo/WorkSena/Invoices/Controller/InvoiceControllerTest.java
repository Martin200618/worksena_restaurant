package com.Trabajo.WorkSena.Invoices.Controller;

import com.Trabajo.WorkSena.Invoices.DTO.InvoiceDto;
import com.Trabajo.WorkSena.Invoices.Service.IInvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InvoiceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IInvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    @Test
    void getAllInvoices_ShouldReturnList() throws Exception {
        List<InvoiceDto> invoices = Arrays.asList(new InvoiceDto(), new InvoiceDto());
        when(invoiceService.getAllInvoices()).thenReturn(invoices);

        mockMvc.perform(get("/api/invoices"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(invoiceService, times(1)).getAllInvoices();
    }

    @Test
    void getInvoiceById_ShouldReturnInvoice() throws Exception {
        InvoiceDto invoice = new InvoiceDto();
        when(invoiceService.getInvoiceById(1L)).thenReturn(java.util.Optional.of(invoice));

        mockMvc.perform(get("/api/invoices/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(invoiceService, times(1)).getInvoiceById(1L);
    }

    @Test
    void getInvoiceById_ShouldReturnNotFound() throws Exception {
        when(invoiceService.getInvoiceById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/invoices/1"))
                .andExpect(status().isNotFound());

        verify(invoiceService, times(1)).getInvoiceById(1L);
    }

    @Test
    void getInvoiceByNumber_ShouldReturnInvoice() throws Exception {
        InvoiceDto invoice = new InvoiceDto();
        when(invoiceService.getInvoiceByNumber("INV001")).thenReturn(invoice);

        mockMvc.perform(get("/api/invoices/number/INV001"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(invoiceService, times(1)).getInvoiceByNumber("INV001");
    }

    @Test
    void getInvoiceByNumber_ShouldReturnNotFound() throws Exception {
        when(invoiceService.getInvoiceByNumber("INV001")).thenReturn(null);

        mockMvc.perform(get("/api/invoices/number/INV001"))
                .andExpect(status().isNotFound());

        verify(invoiceService, times(1)).getInvoiceByNumber("INV001");
    }

    @Test
    void getInvoicesByOrderId_ShouldReturnList() throws Exception {
        List<InvoiceDto> invoices = Arrays.asList(new InvoiceDto());
        when(invoiceService.getInvoicesByOrderId(1L)).thenReturn(invoices);

        mockMvc.perform(get("/api/invoices/order/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(invoiceService, times(1)).getInvoicesByOrderId(1L);
    }

    @Test
    void generateInvoiceFromOrder_ShouldReturnInvoice() throws Exception {
        InvoiceDto invoice = new InvoiceDto();
        when(invoiceService.createInvoiceFromOrder(1L)).thenReturn(invoice);

        mockMvc.perform(post("/api/invoices/generate/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(invoiceService, times(1)).createInvoiceFromOrder(1L);
    }

    @Test
    void generateInvoiceFromOrder_ShouldReturnBadRequest() throws Exception {
        when(invoiceService.createInvoiceFromOrder(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/invoices/generate/1"))
                .andExpect(status().isBadRequest());

        verify(invoiceService, times(1)).createInvoiceFromOrder(1L);
    }

    @Test
    void updateInvoiceStatus_ShouldReturnUpdatedInvoice() throws Exception {
        InvoiceDto updatedInvoice = new InvoiceDto();
        when(invoiceService.updateInvoiceStatus(1L, "PAID")).thenReturn(updatedInvoice);

        mockMvc.perform(put("/api/invoices/1/status")
                .param("status", "PAID"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(invoiceService, times(1)).updateInvoiceStatus(1L, "PAID");
    }

    @Test
    void updateInvoiceStatus_ShouldReturnBadRequest() throws Exception {
        when(invoiceService.updateInvoiceStatus(1L, "PAID")).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/invoices/1/status")
                .param("status", "PAID"))
                .andExpect(status().isBadRequest());

        verify(invoiceService, times(1)).updateInvoiceStatus(1L, "PAID");
    }

    @Test
    void deleteInvoice_ShouldReturnNoContent() throws Exception {
        doNothing().when(invoiceService).deleteInvoice(1L);

        mockMvc.perform(delete("/api/invoices/1"))
                .andExpect(status().isNoContent());

        verify(invoiceService, times(1)).deleteInvoice(1L);
    }

    @Test
    void deleteInvoice_ShouldReturnNotFound() throws Exception {
        doThrow(new RuntimeException()).when(invoiceService).deleteInvoice(1L);

        mockMvc.perform(delete("/api/invoices/1"))
                .andExpect(status().isNotFound());

        verify(invoiceService, times(1)).deleteInvoice(1L);
    }

    @Test
    void getInvoicesByDateRange_ShouldReturnList() throws Exception {
        List<InvoiceDto> invoices = Arrays.asList(new InvoiceDto());
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        when(invoiceService.getInvoicesByDateRange(startDate, endDate)).thenReturn(invoices);

        mockMvc.perform(get("/api/invoices/reports/date-range")
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(invoiceService, times(1)).getInvoicesByDateRange(any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void getTotalRevenueByDateRange_ShouldReturnRevenue() throws Exception {
        BigDecimal revenue = BigDecimal.valueOf(1000.00);
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        when(invoiceService.getTotalRevenueByDateRange(startDate, endDate)).thenReturn(revenue);

        mockMvc.perform(get("/api/invoices/reports/revenue")
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(invoiceService, times(1)).getTotalRevenueByDateRange(any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void countPaidInvoicesByDateRange_ShouldReturnCount() throws Exception {
        Long count = 5L;
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        when(invoiceService.countPaidInvoicesByDateRange(startDate, endDate)).thenReturn(count);

        mockMvc.perform(get("/api/invoices/reports/count-paid")
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(invoiceService, times(1)).countPaidInvoicesByDateRange(any(LocalDateTime.class), any(LocalDateTime.class));
    }
}