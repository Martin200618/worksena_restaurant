package com.Trabajo.WorkSena.Orders.Controller;

import com.Trabajo.WorkSena.Orders.DTO.OrderDto;
import com.Trabajo.WorkSena.Orders.Entity.Order;
import com.Trabajo.WorkSena.Orders.Service.IOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IOrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void getAllOrders_ShouldReturnList() throws Exception {
        List<OrderDto> orders = Arrays.asList(new OrderDto(), new OrderDto());
        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void getOrderById_ShouldReturnOrder() throws Exception {
        OrderDto order = new OrderDto();
        when(orderService.getOrderById(1L)).thenReturn(java.util.Optional.of(order));

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    void getOrderById_ShouldReturnNotFound() throws Exception {
        when(orderService.getOrderById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isNotFound());

        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    void createOrder_ShouldReturnCreatedOrder() throws Exception {
        OrderDto createdOrder = new OrderDto();
        when(orderService.createOrder(any(Order.class))).thenReturn(createdOrder);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    void createOrder_ShouldReturnBadRequest() throws Exception {
        when(orderService.createOrder(any(Order.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    void updateOrder_ShouldReturnUpdatedOrder() throws Exception {
        OrderDto updatedOrder = new OrderDto();
        when(orderService.updateOrder(eq(1L), any(Order.class))).thenReturn(updatedOrder);

        mockMvc.perform(put("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).updateOrder(eq(1L), any(Order.class));
    }

    @Test
    void updateOrder_ShouldReturnBadRequest() throws Exception {
        when(orderService.updateOrder(eq(1L), any(Order.class))).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(orderService, times(1)).updateOrder(eq(1L), any(Order.class));
    }

    @Test
    void deleteOrder_ShouldReturnNoContent() throws Exception {
        doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).deleteOrder(1L);
    }

    @Test
    void deleteOrder_ShouldReturnNotFound() throws Exception {
        doThrow(new RuntimeException()).when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNotFound());

        verify(orderService, times(1)).deleteOrder(1L);
    }

    @Test
    void updateOrderStatus_ShouldReturnUpdatedOrder() throws Exception {
        OrderDto updatedOrder = new OrderDto();
        when(orderService.updateOrderStatus(1L, Order.OrderStatus.CONFIRMED)).thenReturn(updatedOrder);

        mockMvc.perform(put("/api/orders/1/status")
                .param("status", "CONFIRMED"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).updateOrderStatus(1L, Order.OrderStatus.CONFIRMED);
    }

    @Test
    void updateOrderStatus_ShouldReturnBadRequest() throws Exception {
        when(orderService.updateOrderStatus(1L, Order.OrderStatus.CONFIRMED)).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/orders/1/status")
                .param("status", "CONFIRMED"))
                .andExpect(status().isBadRequest());

        verify(orderService, times(1)).updateOrderStatus(1L, Order.OrderStatus.CONFIRMED);
    }

    @Test
    void confirmOrder_ShouldReturnConfirmedOrder() throws Exception {
        OrderDto confirmedOrder = new OrderDto();
        when(orderService.confirmOrder(1L)).thenReturn(confirmedOrder);

        mockMvc.perform(put("/api/orders/1/confirm"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).confirmOrder(1L);
    }

    @Test
    void cancelOrder_ShouldReturnCancelledOrder() throws Exception {
        OrderDto cancelledOrder = new OrderDto();
        when(orderService.cancelOrder(1L)).thenReturn(cancelledOrder);

        mockMvc.perform(put("/api/orders/1/cancel"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).cancelOrder(1L);
    }

    @Test
    void getOrdersByStatus_ShouldReturnList() throws Exception {
        List<OrderDto> orders = Arrays.asList(new OrderDto());
        when(orderService.getOrdersByStatus(Order.OrderStatus.PENDING)).thenReturn(orders);

        mockMvc.perform(get("/api/orders/status/PENDING"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).getOrdersByStatus(Order.OrderStatus.PENDING);
    }

    @Test
    void getOrdersByTable_ShouldReturnList() throws Exception {
        List<OrderDto> orders = Arrays.asList(new OrderDto());
        when(orderService.getOrdersByTable(1L)).thenReturn(orders);

        mockMvc.perform(get("/api/orders/table/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).getOrdersByTable(1L);
    }

    @Test
    void getOrdersByCustomerName_ShouldReturnList() throws Exception {
        List<OrderDto> orders = Arrays.asList(new OrderDto());
        when(orderService.getOrdersByCustomerName("John Doe")).thenReturn(orders);

        mockMvc.perform(get("/api/orders/search")
                .param("customerName", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).getOrdersByCustomerName("John Doe");
    }

    @Test
    void getOrdersByDateRange_ShouldReturnList() throws Exception {
        List<OrderDto> orders = Arrays.asList(new OrderDto());
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        when(orderService.getOrdersByDateRange(startDate, endDate)).thenReturn(orders);

        mockMvc.perform(get("/api/orders/date-range")
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).getOrdersByDateRange(any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void getOrdersByStatusAndDateRange_ShouldReturnList() throws Exception {
        List<OrderDto> orders = Arrays.asList(new OrderDto());
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        when(orderService.getOrdersByStatusAndDateRange(Order.OrderStatus.CONFIRMED, startDate, endDate)).thenReturn(orders);

        mockMvc.perform(get("/api/orders/status/CONFIRMED/date-range")
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).getOrdersByStatusAndDateRange(eq(Order.OrderStatus.CONFIRMED), any(LocalDateTime.class), any(LocalDateTime.class));
    }
}