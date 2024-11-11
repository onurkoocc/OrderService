package com.qrmenu.OrderService.domain.controller;


import com.qrmenu.OrderService.domain.model.enums.OrderStatus;
import com.qrmenu.OrderService.domain.model.request.CreateOrderRequest;
import com.qrmenu.OrderService.domain.model.response.OrderResponse;
import com.qrmenu.OrderService.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody CreateOrderRequest request,  @AuthenticationPrincipal UUID userId) {
        OrderResponse orderResponse = orderService.placeOrder(request, userId);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/tables/{tableId}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByTableId(@PathVariable Long tableId) {
        List<OrderResponse> orderResponse = orderService.getAllOrders(tableId);
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/{orderId}/status")
    @PreAuthorize("hasAnyRole('KITCHEN', 'WAITER')")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok().build();
    }
}
