package com.tetianamakar.customers.controller;

import com.tetianamakar.customers.payload.request.CustomerRequest;
import com.tetianamakar.customers.payload.response.CustomerResponse;
import com.tetianamakar.customers.service.CustomerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Validated
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse addCustomer(@Valid @RequestBody CustomerRequest request) {
        return customerService.addCustomer(request);
    }

    @PutMapping
    public CustomerResponse updateCustomer(@Valid @RequestBody CustomerRequest request) {
        return customerService.updateCustomer(request);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

}
