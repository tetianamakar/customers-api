package com.tetianamakar.customers.service;

import com.tetianamakar.customers.converter.EntityConverter;
import com.tetianamakar.customers.entity.Customer;
import com.tetianamakar.customers.payload.CustomerRequest;
import com.tetianamakar.customers.payload.CustomerResponse;
import com.tetianamakar.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse addCustomer(CustomerRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Customer with such email already exists");
        }
        Customer customer = new Customer();
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());
        Customer savedCustomer = customerRepository.save(customer);

        return EntityConverter.convertCustomer(savedCustomer);
    }

    public CustomerResponse updateCustomer(CustomerRequest request) {
        Customer existingCustomer = findCustomerById(request.getId());
        existingCustomer.setFullName(request.getFullName());
        existingCustomer.setPhone(request.getPhone());
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return EntityConverter.convertCustomer(updatedCustomer);
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return EntityConverter.convertCustomers(customers);
    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = findCustomerById(id);
        return EntityConverter.convertCustomer(customer);
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with such id does not exist"));
    }

    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customer.setActive(false);
        customerRepository.save(customer);
    }
}
