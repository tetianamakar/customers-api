package com.tetianamakar.customers.converter;

import com.tetianamakar.customers.entity.Company;
import com.tetianamakar.customers.payload.response.CompanyResponse;

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


}
