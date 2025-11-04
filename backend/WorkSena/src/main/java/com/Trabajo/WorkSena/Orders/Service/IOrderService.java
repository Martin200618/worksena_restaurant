package com.Trabajo.WorkSena.Orders.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.Trabajo.WorkSena.Orders.DTO.OrderDto;
import com.Trabajo.WorkSena.Orders.Entity.Order;

public interface IOrderService {
    List<OrderDto> getAllOrders();
    Optional<OrderDto> getOrderById(Long id);
    OrderDto createOrder(Order order);
    OrderDto updateOrder(Long id, Order orderDetails);
    void deleteOrder(Long id);

    // Status management
    OrderDto updateOrderStatus(Long id, Order.OrderStatus status);

    // Filtering operations
    List<OrderDto> getOrdersByStatus(Order.OrderStatus status);
    List<OrderDto> getOrdersByTable(Long tableId);
    List<OrderDto> getOrdersByCustomerName(String customerName);
    List<OrderDto> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<OrderDto> getOrdersByStatusAndDateRange(Order.OrderStatus status, LocalDateTime startDate, LocalDateTime endDate);

    // Business operations
    OrderDto confirmOrder(Long id);
    OrderDto cancelOrder(Long id);
}
