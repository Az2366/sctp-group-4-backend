package com.group4.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group4.backend.exception.OrderNotFoundException;
import com.group4.backend.model.Item;
import com.group4.backend.model.Order;
import com.group4.backend.model.OrderItem;
import com.group4.backend.model.OrderStatus;
import com.group4.backend.repository.OrderRepository;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private Item item;
    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample item setup
        item = new Item();
        item.setItemId(1L);
        item.setItemName("Test Item");
        item.setItemDescription("Test Description");
        item.setPrice(100.0);

        // Sample order item setup
        orderItem = new OrderItem();
        orderItem.setItem(null);
        orderItem.setOrder(order);
        orderItem.setQuantityOrdered(1);

        // Sample Order setup
        order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderItems(Arrays.asList(orderItem, orderItem));

    }

    @Test
    void testCreateOrder() {
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.createOrder(order);

        assertNotNull(savedOrder);
        assertEquals(OrderStatus.COMPLETED, savedOrder.getOrderStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testGetOrderById_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order foundOrder = orderService.getOrderById(1L);

        assertNotNull(foundOrder);
        assertEquals(OrderStatus.COMPLETED, foundOrder.getOrderStatus());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(1L));
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order));

        var orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetAllOrders_NoOrders() {
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());

        var orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertTrue(orders.isEmpty());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testUpdateOrder_Success() {
        Order updatedOrder = new Order();
        updatedOrder.setOrderId(1L);
        updatedOrder.setOrderStatus(OrderStatus.COMPLETED);
        updatedOrder.setOrderDateTime(LocalDateTime.now().plusDays(1));
        updatedOrder.setOrderItems(Arrays.asList(orderItem, orderItem));

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(updatedOrder);

        Order result = orderService.updateOrder(updatedOrder);

        assertNotNull(result);
        assertEquals(OrderStatus.COMPLETED, result.getOrderStatus());
        assertEquals(2, result.getOrderItems().size());
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateOrder_NotFound() {
        Order updatedOrder = new Order();
        updatedOrder.setOrderId(1L);

        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrder(updatedOrder));
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteOrder_Success() {
        when(orderRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = orderService.deleteOrder(1L);

        assertTrue(isDeleted);
        verify(orderRepository, times(1)).existsById(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteOrder_NotFound() {
        when(orderRepository.existsById(1L)).thenReturn(false);

        boolean isDeleted = orderService.deleteOrder(1L);

        assertFalse(isDeleted);
        verify(orderRepository, times(1)).existsById(1L);
        verify(orderRepository, never()).deleteById(1L);
    }
}
