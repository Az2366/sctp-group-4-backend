package com.group4.backend.service;

import java.util.List;

import com.group4.backend.model.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long customerId);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long customerId);

    List<Customer> getAllCustomers();

    List<Customer> getAllCustomersWithoutOrders();
}
