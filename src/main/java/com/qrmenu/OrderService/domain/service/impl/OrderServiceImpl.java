package com.qrmenu.OrderService.domain.service.impl;


import com.qrmenu.OrderService.delegate.client.MenuServiceClient;
import com.qrmenu.OrderService.delegate.model.dto.ProductDTO;
import com.qrmenu.OrderService.domain.dao.OrderDAO;
import com.qrmenu.OrderService.domain.mapper.OrderMapper;
import com.qrmenu.OrderService.domain.model.dto.OrderDTO;
import com.qrmenu.OrderService.domain.model.dto.OrderItemDTO;
import com.qrmenu.OrderService.domain.model.enums.OrderStatus;
import com.qrmenu.OrderService.domain.model.request.CreateOrderRequest;
import com.qrmenu.OrderService.domain.model.response.OrderResponse;
import com.qrmenu.OrderService.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private MenuServiceClient menuServiceClient;

    @Autowired
    private OrderMapper orderMapper;

    public OrderResponse placeOrder(CreateOrderRequest request, UUID customerId) {
        // Map request to DTO
        OrderDTO orderDTO = orderMapper.requestToDTO(request);
        orderDTO.setCustomerId(customerId);
        orderDTO.setOrderTime(LocalDateTime.now());
        orderDTO.setStatus(OrderStatus.NEW);

        // Fetch product details and set in order items
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            ProductDTO product = menuServiceClient.getProductById(itemDTO.getProductId());
            itemDTO.setProductName(product.getName());
            itemDTO.setPrice(product.getPrice());
        }

        // Save order using DAO
        OrderDTO savedOrderDTO = orderDAO.saveOrder(orderDTO);

        // Map saved DTO to response
        return orderMapper.dtoToResponse(savedOrderDTO);
    }

    public OrderResponse getOrderById(Long orderId) {
        OrderDTO orderDTO = orderDAO.getOrderById(orderId);
        return orderMapper.dtoToResponse(orderDTO);
    }

    public void updateOrderStatus(Long orderId, OrderStatus status) {
        orderDAO.updateOrderStatus(orderId, status);
    }

    // Additional service methods...
}
