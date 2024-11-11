package com.qrmenu.OrderService.domain.mapper;

import com.qrmenu.OrderService.domain.model.dto.OrderDTO;
import com.qrmenu.OrderService.domain.model.dto.OrderItemDTO;
import com.qrmenu.OrderService.domain.model.entity.Order;
import com.qrmenu.OrderService.domain.model.entity.OrderItem;
import com.qrmenu.OrderService.domain.model.request.CreateOrderRequest;
import com.qrmenu.OrderService.domain.model.request.OrderItemRequest;
import com.qrmenu.OrderService.domain.model.response.OrderItemResponse;
import com.qrmenu.OrderService.domain.model.response.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order dtoToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setRestaurantId(orderDTO.getRestaurantId());
        order.setTableId(orderDTO.getTableId());
        order.setOrderTime(orderDTO.getOrderTime());
        order.setStatus(orderDTO.getStatus());

        List<OrderItem> items = orderDTO.getItems().stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
        order.setOrderItems(items);

        // Set back-reference in items
        for (OrderItem item : items) {
            item.setOrder(order);
        }

        return order;
    }

    public OrderItem dtoToEntity(OrderItemDTO itemDTO) {
        OrderItem item = new OrderItem();
        item.setId(itemDTO.getId());
        item.setProductId(itemDTO.getProductId());
        item.setProductName(itemDTO.getProductName());
        item.setPrice(itemDTO.getPrice());
        item.setQuantity(itemDTO.getQuantity());
        return item;
    }

    public List<OrderDTO> entityToDto(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new ArrayList<>();
        }
        return orders.stream().map(this::entityToDTO).toList();
    }

    public OrderDTO entityToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setRestaurantId(order.getRestaurantId());
        orderDTO.setTableId(order.getTableId());
        orderDTO.setOrderTime(order.getOrderTime());
        orderDTO.setStatus(order.getStatus());

        List<OrderItemDTO> items = order.getOrderItems().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        orderDTO.setItems(items);

        return orderDTO;
    }

    public OrderItemDTO entityToDTO(OrderItem item) {
        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setProductId(item.getProductId());
        itemDTO.setProductName(item.getProductName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setQuantity(item.getQuantity());
        return itemDTO;
    }


    public OrderDTO requestToDTO(CreateOrderRequest request) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setRestaurantId(request.getRestaurantId());
        orderDTO.setTableId(request.getTableId());

        List<OrderItemDTO> items = request.getItems().stream()
                .map(this::requestToDTO)
                .collect(Collectors.toList());
        orderDTO.setItems(items);

        return orderDTO;
    }

    public OrderItemDTO requestToDTO(OrderItemRequest itemRequest) {
        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setProductId(itemRequest.getProductId());
        itemDTO.setQuantity(itemRequest.getQuantity());
        return itemDTO;
    }

    public List<OrderResponse> dtoToResponseList(List<OrderDTO> orders) {
        if (orders == null || orders.isEmpty()) {
            return new ArrayList<>();
        }
        return orders.stream().map(this::orderDtoToResponse).collect(Collectors.toList());
    }

    public OrderResponse orderDtoToResponse(OrderDTO orderDTO) {
        OrderResponse response = new OrderResponse();
        response.setId(orderDTO.getId());
        response.setCustomerId(orderDTO.getCustomerId());
        response.setRestaurantId(orderDTO.getRestaurantId());
        response.setTableId(orderDTO.getTableId());
        response.setOrderTime(orderDTO.getOrderTime());
        response.setStatus(orderDTO.getStatus());

        List<OrderItemResponse> items = orderDTO.getItems().stream()
                .map(this::orderItemDtoToResponse)
                .collect(Collectors.toList());
        response.setItems(items);

        return response;
    }

    public OrderItemResponse orderItemDtoToResponse(OrderItemDTO itemDTO) {
        OrderItemResponse itemResponse = new OrderItemResponse();
        itemResponse.setProductId(itemDTO.getProductId());
        itemResponse.setProductName(itemDTO.getProductName());
        itemResponse.setPrice(itemDTO.getPrice());
        itemResponse.setQuantity(itemDTO.getQuantity());
        return itemResponse;
    }
}
