package com.qrmenu.OrderService.domain.repository;

import com.qrmenu.OrderService.domain.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByTableId(Long tableId);
}
