package com.tetianamakar.customers.service;

import com.tetianamakar.customers.entity.Customer;
import com.tetianamakar.customers.payload.request.CustomerRequest;
import com.tetianamakar.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(CustomerRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Customer with such email already exists");
        }
        Customer customer = new Customer();
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());
        customerRepository.save(customer);
    }
}
