package com.tetianamakar.customers.controller;

import com.tetianamakar.customers.payload.response.CompanyResponse;
import com.tetianamakar.customers.service.CompanyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest()
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    @DisplayName("Should return all companies")
    void testGetAllCompanies() throws Exception {
        List<CompanyResponse> companyResponses = generateCompanyResponses();
        when(companyService.getAllCompanies()).thenReturn(companyResponses);
        mockMvc.perform(get("/companies/all"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(companyResponses.get(0).getId().toString()))
            .andExpect(jsonPath("$[0].name").value(companyResponses.get(0).getName()))
            .andExpect(jsonPath("$[0].registrationNumber").value(companyResponses.get(0).getRegistrationNumber()))
            .andExpect(jsonPath("$[0].address").value(companyResponses.get(0).getAddress()));
    }

    @Test
    @DisplayName("Should add a new company")
    void testAddCompany() throws Exception {
        mockMvc.perform(post("/companies/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Company\",\"registrationNumber\":\"1234567890\",\"address\":\"Test Address\"}")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should delete a company by ID")
    void testDeleteCompany() throws Exception {
        UUID companyId = UUID.randomUUID();
        mockMvc.perform(delete("/companies/delete/{id}", companyId))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should update an existing company")
    void testUpdateCompany() throws Exception {
        mockMvc.perform(put("/companies/update/{id}", UUID.fromString("123e4567-e89b-12d3-a456-556642440000"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Test Company\",\"registrationNumber\":\"0987654321\",\"address\":\"Updated Test Address\"}"))
            .andExpect(status().isOk());
    }

    private List<CompanyResponse> generateCompanyResponses() {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        CompanyResponse response1 = new CompanyResponse();
        response1.setId(UUID.randomUUID());
        response1.setName("Company 1");
        response1.setRegistrationNumber("123456");
        response1.setAddress("Address 1");
        companyResponses.add(response1);
        CompanyResponse response2 = new CompanyResponse();
        response2.setId(UUID.randomUUID());
        response2.setName("Company 2");
        response2.setRegistrationNumber("654321");
        response2.setAddress("Address 2");
        companyResponses.add(response2);
        return companyResponses;
    }

}
