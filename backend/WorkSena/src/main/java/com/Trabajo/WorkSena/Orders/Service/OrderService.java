package com.Trabajo.WorkSena.Orders.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trabajo.WorkSena.Orders.DTO.OrderDto;
import com.Trabajo.WorkSena.Orders.DTO.OrderItemDto;
import com.Trabajo.WorkSena.Orders.Entity.Order;
import com.Trabajo.WorkSena.Orders.Entity.OrderMenuItem;
import com.Trabajo.WorkSena.Orders.Repository.IOrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDto> getOrderById(Long id) {
        return orderRepository.findById(id)
            .map(this::convertToDto);
    }

    @Override
    public OrderDto createOrder(Order order) {
        // Calculate total amount from order items
        calculateTotalAmount(order);

        Order savedOrder = orderRepository.save(order);
        return convertToDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setTableId(orderDetails.getTableId());
        order.setCustomerName(orderDetails.getCustomerName());
        order.setCustomerPhone(orderDetails.getCustomerPhone());
        order.setNotes(orderDetails.getNotes());

        // Recalculate total if items changed
        if (orderDetails.getOrderMenuItems() != null) {
            order.setOrderMenuItems(orderDetails.getOrderMenuItems());
            calculateTotalAmount(order);
        }

        Order updatedOrder = orderRepository.save(order);
        return convertToDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public OrderDto updateOrderStatus(Long id, Order.OrderStatus status) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return convertToDto(updatedOrder);
    }

    @Override
    public List<OrderDto> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByTable(Long tableId) {
        return orderRepository.findByTableId(tableId).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCustomerName(String customerName) {
        return orderRepository.findByCustomerNameContainingIgnoreCase(customerName).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByDateRange(startDate, endDate).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByStatusAndDateRange(Order.OrderStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByStatusAndDateRange(status, startDate, endDate).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public OrderDto confirmOrder(Long id) {
        return updateOrderStatus(id, Order.OrderStatus.CONFIRMED);
    }

    @Override
    public OrderDto cancelOrder(Long id) {
        return updateOrderStatus(id, Order.OrderStatus.CANCELLED);
    }

    private void calculateTotalAmount(Order order) {
        if (order.getOrderMenuItems() != null) {
            BigDecimal total = order.getOrderMenuItems().stream()
                .map(OrderMenuItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalAmount(total);
        }
    }

    private OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setTableId(order.getTableId());
        dto.setCustomerName(order.getCustomerName());
        dto.setCustomerPhone(order.getCustomerPhone());
        dto.setStatus(order.getStatus().toString());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setNotes(order.getNotes());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());

        if (order.getOrderMenuItems() != null) {
            List<OrderItemDto> items = order.getOrderMenuItems().stream()
                .map(this::convertOrderItemToDto)
                .collect(Collectors.toList());
            dto.setItems(items);
        }

        return dto;
    }

    private OrderItemDto convertOrderItemToDto(OrderMenuItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(item.getId());
        dto.setMenuItemId(item.getMenuItemId());
        dto.setMenuItemName(item.getMenuItemName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setTotalPrice(item.getTotalPrice());
        dto.setNotes(item.getNotes());
        return dto;
    }
}
