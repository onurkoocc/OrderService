package com.qrmenu.OrderService.domain.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull(message = "Restaurant ID cannot be null")
    private Long restaurantId;
    @NotNull(message = "Table ID cannot be null")
    private Long tableId;
    @NotEmpty(message = "Order items cannot be empty")
    private List<OrderItemRequest> items;
}
