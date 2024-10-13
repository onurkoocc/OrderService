package com.qrmenu.OrderService.domain.service;

import com.qrmenu.OrderService.domain.model.enums.OrderStatus;
import com.qrmenu.OrderService.domain.model.request.CreateOrderRequest;
import com.qrmenu.OrderService.domain.model.response.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse placeOrder(CreateOrderRequest request, UUID customerId);
    OrderResponse getOrderById(Long orderId);
    void updateOrderStatus(Long orderId, OrderStatus status);
}
