package com.group4.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.backend.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
