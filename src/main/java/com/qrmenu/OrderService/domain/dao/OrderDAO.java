package com.qrmenu.OrderService.domain.dao;

import com.qrmenu.OrderService.domain.mapper.OrderMapper;
import com.qrmenu.OrderService.domain.model.dto.OrderDTO;
import com.qrmenu.OrderService.domain.model.entity.Order;
import com.qrmenu.OrderService.domain.model.enums.OrderStatus;
import com.qrmenu.OrderService.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        // Convert DTO to entity
        Order orderEntity = orderMapper.dtoToEntity(orderDTO);

        // Save entity
        Order savedOrder = orderRepository.save(orderEntity);

        // Convert saved entity back to DTO
        return orderMapper.entityToDTO(savedOrder);
    }

    public OrderDTO getOrderById(Long orderId) {
        Order orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.entityToDTO(orderEntity);
    }

    public void updateOrderStatus(Long orderId, OrderStatus status) {
        Order orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderEntity.setStatus(status);
        orderRepository.save(orderEntity);
    }

    // Additional DAO methods...
}
