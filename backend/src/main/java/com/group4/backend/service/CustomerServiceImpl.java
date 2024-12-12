package com.group4.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group4.backend.exception.CustomerNotFoundException;
import com.group4.backend.model.Customer;
import com.group4.backend.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer) {
        if (customer.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID must not be null");
        }
        Customer existingCustomer = customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(customer.getCustomerId()));
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setHomeAddress(customer.getHomeAddress());
        existingCustomer.setActive(customer.isActive());
        existingCustomer.setDateOfBirth(customer.getDateOfBirth());
        existingCustomer.setMembershipType(customer.getMembershipType());
        return customerRepository.save(existingCustomer);
    }

    @Override
    @Transactional
    public boolean deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true; // Successfully deleted
        }
        return false; // Customer not found
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getAllCustomersWithoutOrders() {
        return customerRepository.findCustomersWithoutOrders();
    }

}
