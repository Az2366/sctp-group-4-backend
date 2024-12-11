package com.group4.backend.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.group4.backend.model.Customer;
import com.group4.backend.model.Item;
import com.group4.backend.model.Membership;
import com.group4.backend.model.Order;
import com.group4.backend.model.OrderItem;
import com.group4.backend.model.OrderStatus;
import com.group4.backend.repository.CustomerRepository;
import com.group4.backend.repository.ItemRepository;
import com.group4.backend.repository.OrderItemRepository;
import com.group4.backend.repository.OrderRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public DataLoader(
            CustomerRepository customerRepository,
            ItemRepository itemRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @PostConstruct
    private void loadData() {
        // Load data here

        Customer customer1 = Customer.builder()
                .customerName("Customer1")
                .email("customer1@email.com")
                .phoneNumber("56781234")
                .homeAddress("789 Oak Avenue")
                .isActive(true)
                .dateOfBirth("2000-03-20")
                .membershipType(Membership.SILVER)
                .orders(null) // No orders initially
                .build();

        Customer customer2 = Customer.builder()
                .customerName("customer2")
                .email("customer2@email.com")
                .phoneNumber("56781234")
                .homeAddress("789 Oak Avenue")
                .isActive(true)
                .dateOfBirth("2000-03-20")
                .membershipType(Membership.BRONZE)
                .orders(null) // No orders initially
                .build();

        Customer customer3 = Customer.builder()
                .customerName("customer3")
                .email("customer3@email.com")
                .phoneNumber("56781234")
                .homeAddress("789 Oak Avenue")
                .isActive(true)
                .dateOfBirth("2000-03-20")
                .membershipType(Membership.GOLD)
                .orders(null) // No orders initially
                .build();

        List<Customer> customers = Arrays.asList(customer1, customer2, customer3);
        customerRepository.saveAll(customers);

        // Create sample items
        Item item1 = Item.builder()
                .itemName("Laptop")
                .itemDescription("The best in class laptop")
                .price(1500d)
                .build();
        itemRepository.save(item1);

        Item item2 = Item.builder()
                .itemName("Mouse")
                .itemDescription("The best in class mouse")
                .price(14.99d)
                .build();

        List<Item> items = Arrays.asList(item1, item2);
        itemRepository.saveAll(items);

        // Create sample orders
        Order order1 = Order.builder()
                .customer(customer1)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.PENDING_PACKING)
                .build();

        Order order2 = Order.builder()
                .customer(customer2)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.COMPLETED)
                .build();

        Order order3 = Order.builder()
                .customer(customer2)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.COMPLETED)
                .build();
        List<Order> orders = Arrays.asList(order1, order2, order3);
        orderRepository.saveAll(orders);

        // Create sample order items
        OrderItem orderItem1 = OrderItem.builder()
                .order(order1)
                .item(item1)
                .quantityOrdered(20)
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .order(order1)
                .item(item2)
                .quantityOrdered(2)
                .build();

        OrderItem orderItem3 = OrderItem.builder()
                .order(order2)
                .item(item2)
                .quantityOrdered(3)
                .build();
        List<OrderItem> orderItems = Arrays.asList(orderItem1, orderItem2, orderItem3);
        orderItemRepository.saveAll(orderItems);
    }
}
