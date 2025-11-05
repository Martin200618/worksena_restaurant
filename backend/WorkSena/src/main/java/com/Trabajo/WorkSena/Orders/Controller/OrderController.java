package com.Trabajo.WorkSena.Orders.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Trabajo.WorkSena.Orders.DTO.OrderDto;
import com.Trabajo.WorkSena.Orders.Entity.Order;
import com.Trabajo.WorkSena.Orders.Service.IOrderService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
@Tag(name = "Orders", description = "API para gestión de órdenes del restaurante")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
            .map(order -> ResponseEntity.ok(order))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva orden", description = "Crea una nueva orden en el restaurante con la información proporcionada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orden creada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content)
    })
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        try {
            // Convertir DTO a entidad
            Order order = new Order();
            order.setTableId(orderDto.getTableId());
            order.setCustomerName(orderDto.getCustomerName());
            order.setCustomerPhone(orderDto.getCustomerPhone());
            order.setStatus(Order.OrderStatus.valueOf(orderDto.getStatus().toUpperCase()));
            order.setNotes(orderDto.getNotes());

            // Convertir items
            if (orderDto.getItems() != null) {
                // Aquí necesitaríamos lógica adicional para convertir OrderItemDto a OrderMenuItem
                // Por simplicidad, asumiremos que se maneja en el servicio
            }

            OrderDto createdOrder = orderService.createOrder(order);
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una orden", description = "Actualiza la información de una orden existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orden actualizada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content)
    })
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        try {
            // Convertir DTO a entidad
            Order order = new Order();
            order.setTableId(orderDto.getTableId());
            order.setCustomerName(orderDto.getCustomerName());
            order.setCustomerPhone(orderDto.getCustomerPhone());
            order.setStatus(Order.OrderStatus.valueOf(orderDto.getStatus().toUpperCase()));
            order.setNotes(orderDto.getNotes());

            OrderDto updatedOrder = orderService.updateOrder(id, order);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Status management endpoints
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @RequestParam Order.OrderStatus status) {
        try {
            OrderDto updatedOrder = orderService.updateOrderStatus(id, status);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<OrderDto> confirmOrder(@PathVariable Long id) {
        try {
            OrderDto confirmedOrder = orderService.confirmOrder(id);
            return ResponseEntity.ok(confirmedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long id) {
        try {
            OrderDto cancelledOrder = orderService.cancelOrder(id);
            return ResponseEntity.ok(cancelledOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Filtering endpoints
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderDto>> getOrdersByStatus(@PathVariable Order.OrderStatus status) {
        List<OrderDto> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/table/{tableId}")
    public ResponseEntity<List<OrderDto>> getOrdersByTable(@PathVariable Long tableId) {
        List<OrderDto> orders = orderService.getOrdersByTable(tableId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerName(@RequestParam String customerName) {
        List<OrderDto> orders = orderService.getOrdersByCustomerName(customerName);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<OrderDto>> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<OrderDto> orders = orderService.getOrdersByDateRange(startDate, endDate);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status/{status}/date-range")
    public ResponseEntity<List<OrderDto>> getOrdersByStatusAndDateRange(
            @PathVariable Order.OrderStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<OrderDto> orders = orderService.getOrdersByStatusAndDateRange(status, startDate, endDate);
        return ResponseEntity.ok(orders);
    }
}
