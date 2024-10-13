package com.qrmenu.OrderService.domain.model.request;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CreateOrderRequest {
    private UUID restaurantId;
    private Long tableId;
    private List<OrderItemRequest> items;
}
