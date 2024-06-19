package com.tetianamakar.customers.converter;

import com.tetianamakar.customers.entity.Company;
import com.tetianamakar.customers.entity.Customer;
import com.tetianamakar.customers.payload.response.CompanyResponse;
import com.tetianamakar.customers.payload.response.CustomerResponse;

import java.util.List;
import java.util.stream.Collectors;

public class EntityConverter {



    public static List<CompanyResponse> convertCompanies(List<Company> companies) {
        return companies.stream()
                .map(EntityConverter::convertCompany)
                .collect(Collectors.toList());
    }

    private static CompanyResponse convertCompany(Company company) {
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setName(company.getName());
        response.setRegistrationNumber(company.getRegistrationNumber());
        response.setCreatedAt(company.getCreatedAt());
        response.setAddress(company.getAddress());
        return response;
    }

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
