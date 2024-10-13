package com.qrmenu.OrderService.delegate.model.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String title;
    private String description;
    private double price;
    private String photoUrl;
}
