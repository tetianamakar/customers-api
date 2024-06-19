package com.tetianamakar.customers.converter;

import com.tetianamakar.customers.entity.Customer;
import com.tetianamakar.customers.payload.CustomerResponse;

import java.util.List;
import java.util.stream.Collectors;

public class EntityConverter {

    public static CustomerResponse convertCustomer(Customer savedCustomer) {
        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setId(savedCustomer.getId());
        customerResponse.setFullName(savedCustomer.getFullName());
        customerResponse.setEmail(savedCustomer.getEmail());
        customerResponse.setPhone(savedCustomer.getPhone());
        return customerResponse;
    }

    public static List<CustomerResponse> convertCustomers(List<Customer> customers) {
        return customers.stream()
                .map(EntityConverter::convertCustomer)
                .collect(Collectors.toList());
    }

}
