package com.tetianamakar.customers.repository;

import com.tetianamakar.customers.entity.Company;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, UUID> {


}
