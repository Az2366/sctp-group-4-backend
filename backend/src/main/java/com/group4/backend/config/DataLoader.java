package com.group4.backend.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.group4.backend.model.Customer;
import com.group4.backend.model.Item;
import com.group4.backend.model.Membership;
import com.group4.backend.repository.CustomerRepository;
import com.group4.backend.repository.ItemRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public DataLoader(
            CustomerRepository customerRepository,
            ItemRepository itemRepository) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
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

    }
}
