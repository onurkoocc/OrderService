package com.qrmenu.OrderService.domain.model.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}
