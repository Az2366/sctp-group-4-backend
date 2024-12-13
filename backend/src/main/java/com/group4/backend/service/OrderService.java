package com.group4.backend.service;

import java.util.List;

import com.group4.backend.model.Order;

public interface OrderService {
    Order createOrder(Order order);

    Order getOrderById(Long orderId);

    List<Order> getAllOrders();

    Order updateOrder(Order updatedOrder);

    boolean deleteOrder(Long orderId);
}
