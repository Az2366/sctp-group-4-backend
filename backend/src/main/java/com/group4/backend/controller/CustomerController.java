package com.group4.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.backend.model.Customer;
import com.group4.backend.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customers", description = "These are all the endpoints related to customer controller")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new customer", description = "Register a new customer based on the provided details")
    public ResponseEntity<Customer> register(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Fetches customer details by their unique ID")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle 404 if customer not found
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an existing customer", description = "Updates the customer details based on the provided ID and data")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer);
        if (updatedCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle 404 if customer not found
        }
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer", description = "Deletes the customer based on their unique ID")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        boolean isDeleted = customerService.deleteCustomer(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle 404 if customer not found
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all customers", description = "Fetches all customers from the database")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/withoutOrders")
    @Operation(summary = "Get customers without orders", description = "Fetches all customers who do not have any orders")
    public ResponseEntity<List<Customer>> getAllCustomersWithoutOrders() {
        List<Customer> customers = customerService.getAllCustomersWithoutOrders();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
