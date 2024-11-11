package com.qrmenu.OrderService.domain.model.response;


import com.qrmenu.OrderService.domain.model.enums.OrderStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderResponse {
    private Long id;
    private UUID customerId;
    private Long restaurantId;
    private Long tableId;
    private LocalDateTime orderTime;
    private OrderStatus status;
    private List<OrderItemResponse> items;
}

