package com.group4.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.backend.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
