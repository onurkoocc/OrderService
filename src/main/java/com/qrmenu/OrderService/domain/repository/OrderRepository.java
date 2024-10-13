package com.qrmenu.OrderService.domain.repository;

import com.qrmenu.OrderService.domain.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
