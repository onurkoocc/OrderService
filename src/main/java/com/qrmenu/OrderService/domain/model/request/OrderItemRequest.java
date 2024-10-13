package com.qrmenu.OrderService.domain.model.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private int quantity;
}
