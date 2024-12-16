package com.group4.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group4.backend.exception.CustomerNotFoundException;
import com.group4.backend.model.Customer;
import com.group4.backend.model.Membership;
import com.group4.backend.repository.CustomerRepository;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample customer setup
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("John Doe");
        customer.setEmail("johndoe@example.com");
        customer.setPhoneNumber("123456789");
        customer.setHomeAddress("123 Street, City");
        customer.setActive(true);
        customer.setDateOfBirth("1990-01-01");
        customer.setMembershipType(Membership.GOLD);
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getCustomerName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testGetCustomerById_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(1L);

        assertNotNull(foundCustomer);
        assertEquals("John Doe", foundCustomer.getCustomerName());
        assertEquals("johndoe@example.com", foundCustomer.getEmail());
        assertEquals("123456789", foundCustomer.getPhoneNumber());
        assertEquals("123 Street, City", foundCustomer.getHomeAddress());
        assertEquals(true, foundCustomer.isActive());
        assertEquals("1990-01-01", foundCustomer.getDateOfBirth());
        assertEquals(Membership.GOLD, foundCustomer.getMembershipType());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCustomerById_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateCustomer_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customer.setCustomerName("Jane Doe");
        customer.setActive(false);
        Customer updatedCustomer = customerService.updateCustomer(customer);

        assertNotNull(updatedCustomer);
        assertEquals("Jane Doe", updatedCustomer.getCustomerName());
        assertEquals(false, updatedCustomer.isActive());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testUpdateCustomer_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(customer));
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateCustomer_NullId() {
        customer.setCustomerId(null);

        assertThrows(IllegalArgumentException.class, () -> customerService.updateCustomer(customer));
        verify(customerRepository, never()).findById(anyLong());
    }

    @Test
    void testDeleteCustomer_Success() {
        when(customerRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = customerService.deleteCustomer(1L);

        assertTrue(isDeleted);
        verify(customerRepository, times(1)).existsById(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCustomer_NotFound() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        boolean isDeleted = customerService.deleteCustomer(1L);

        assertFalse(isDeleted);
        verify(customerRepository, times(1)).existsById(1L);
        verify(customerRepository, never()).deleteById(1L);
    }

    @Test
    void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

        var customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertEquals(1, customers.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetAllCustomers_NoCustomers() {
        when(customerRepository.findAll()).thenReturn(Collections.emptyList());

        var customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertTrue(customers.isEmpty());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetAllCustomersWithoutOrders() {
        when(customerRepository.findCustomersWithoutOrders()).thenReturn(Arrays.asList(customer));

        var customersWithoutOrders = customerService.getAllCustomersWithoutOrders();

        assertNotNull(customersWithoutOrders);
        assertEquals(1, customersWithoutOrders.size());
        verify(customerRepository, times(1)).findCustomersWithoutOrders();
    }

    @Test
    void testGetAllCustomersWithoutOrders_NoCustomers() {
        when(customerRepository.findCustomersWithoutOrders()).thenReturn(Collections.emptyList());

        var customersWithoutOrders = customerService.getAllCustomersWithoutOrders();

        assertNotNull(customersWithoutOrders);
        assertTrue(customersWithoutOrders.isEmpty());
        verify(customerRepository, times(1)).findCustomersWithoutOrders();
    }
}
