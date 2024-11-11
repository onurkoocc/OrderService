package com.qrmenu.OrderService.domain.model.dto;

import com.qrmenu.OrderService.domain.model.enums.OrderStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private Long id;
    private UUID customerId;
    private Long restaurantId;
    private Long tableId;
    private LocalDateTime orderTime;
    private OrderStatus status;
    private List<OrderItemDTO> items;
}
