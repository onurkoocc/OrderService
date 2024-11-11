package com.qrmenu.OrderService.domain.service.impl;


import com.qrmenu.OrderService.delegate.model.dto.ProductDTO;
import com.qrmenu.OrderService.delegate.service.MenuServiceDelegate;
import com.qrmenu.OrderService.domain.dao.OrderDAO;
import com.qrmenu.OrderService.domain.mapper.OrderMapper;
import com.qrmenu.OrderService.domain.model.dto.OrderDTO;
import com.qrmenu.OrderService.domain.model.dto.OrderItemDTO;
import com.qrmenu.OrderService.domain.model.enums.OrderStatus;
import com.qrmenu.OrderService.domain.model.request.CreateOrderRequest;
import com.qrmenu.OrderService.domain.model.response.OrderResponse;
import com.qrmenu.OrderService.domain.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final MenuServiceDelegate menuServiceDelegate;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, MenuServiceDelegate menuServiceDelegate, OrderMapper orderMapper) {
        this.orderDAO = orderDAO;
        this.menuServiceDelegate = menuServiceDelegate;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponse placeOrder(CreateOrderRequest request, UUID customerId) {
        OrderDTO orderDTO = prepareOrderDTO(request, customerId);
        enrichOrderItems(orderDTO);
        OrderDTO savedOrderDTO = orderDAO.saveOrder(orderDTO);
        return orderMapper.orderDtoToResponse(savedOrderDTO);
    }

    private OrderDTO prepareOrderDTO(CreateOrderRequest request, UUID customerId) {
        OrderDTO orderDTO = orderMapper.requestToDTO(request);
        orderDTO.setCustomerId(customerId);
        orderDTO.setOrderTime(LocalDateTime.now());
        orderDTO.setStatus(OrderStatus.NEW);
        return orderDTO;
    }

    private void enrichOrderItems(OrderDTO orderDTO) {
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            ProductDTO product = menuServiceDelegate.getProductById(itemDTO.getProductId());
            itemDTO.setProductName(product.getName());
            itemDTO.setPrice(product.getPrice());
        }
    }

    public OrderResponse getOrderById(Long orderId) {
        OrderDTO orderDTO = orderDAO.getOrderById(orderId);
        return orderMapper.orderDtoToResponse(orderDTO);
    }

    public void updateOrderStatus(Long orderId, OrderStatus status) {
        orderDAO.updateOrderStatus(orderId, status);
    }

    public List<OrderResponse> getAllOrders(Long tableId) {
        return orderMapper.dtoToResponseList(orderDAO.getAllOrdersByTableId(tableId));
    }
}
