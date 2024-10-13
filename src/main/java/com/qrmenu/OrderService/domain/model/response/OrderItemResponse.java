package com.qrmenu.OrderService.domain.model.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}