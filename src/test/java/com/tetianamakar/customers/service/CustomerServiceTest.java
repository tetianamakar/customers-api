package com.tetianamakar.customers.service;

import com.tetianamakar.customers.converter.EntityConverter;
import com.tetianamakar.customers.entity.Customer;
import com.tetianamakar.customers.payload.CustomerRequest;
import com.tetianamakar.customers.payload.CustomerResponse;
import com.tetianamakar.customers.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Should add a customer")
    void testAddCustomer() {
        CustomerRequest request = createCustomerRequest();

        Customer savedCustomer = createCustomer(1L, "test@example.com", "Test User", "+380930022333", true);

        when(customerRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerResponse result = customerService.addCustomer(request);

        assertEquals("test@example.com", result.getEmail());
        assertEquals("Test User", result.getFullName());
        assertEquals("+380930022333", result.getPhone());
    }

    @Test
    @DisplayName("Should throw exception when customer with such email already exists")
    void testAddCustomerAlreadyExists() {
        CustomerRequest request = createCustomerRequest();

        when(customerRepository.existsByEmail(request.getEmail())).thenReturn(true);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> customerService.addCustomer(request));

        assertEquals("Customer with such email already exists", exception.getMessage());
    }

    @Test
    @DisplayName("Should update a customer")
    void testUpdateCustomer() {
        CustomerRequest request = createCustomerRequest();
        request.setId(1L);

        Customer customer = createCustomer(1L, "test@example.com", "Old User", "123456789", true);
        Customer updatedCustomer = createCustomer(1L, "test@example.com", "Updated User", "987654321", true);

        when(customerRepository.findById(request.getId())).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(updatedCustomer);

        CustomerResponse result = customerService.updateCustomer(request);
        assertEquals("Updated User", result.getFullName());
    }

    @Test
    @DisplayName("Should return all customers")
    void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(
                createCustomer(1L, "test1@example.com", "Test User 1", "123456789", true),
                createCustomer(2L, "test2@example.com", "Test User 2", "987654321", true)
        );
        List<CustomerResponse> responses = EntityConverter.convertCustomers(customers);

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerResponse> result = customerService.getAllCustomers();

        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return a customer by ID")
    void testGetCustomer() {
        Customer customer = createCustomer(1L, "test@example.com", "Test User", "123456789", true);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerResponse result = customerService.getCustomer(1L);

        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    @DisplayName("Should throw exception when customer not found by ID")
    void testGetCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> customerService.getCustomer(1L));

        assertEquals("Customer with such id does not exist", exception.getMessage());
    }

    @Test
    @DisplayName("Should delete a customer by ID")
    void testDeleteCustomer() {
        Customer customer = createCustomer(1L, "test@example.com", "Test User", "123456789", true);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).save(customer);
        assertFalse(customer.isActive());
    }

    private Customer createCustomer(Long id, String email, String fullName, String phone, Boolean isActive) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setEmail(email);
        customer.setFullName(fullName);
        customer.setPhone(phone);
        customer.setActive(isActive);
        return customer;
    }

    private static CustomerRequest createCustomerRequest() {
        CustomerRequest request = new CustomerRequest();
        String email = "test@example.com";
        String fullName = "Test User";
        String phone = "+380930022333";

        request.setEmail(email);
        request.setFullName(fullName);
        request.setPhone(phone);
        return request;
    }
}
