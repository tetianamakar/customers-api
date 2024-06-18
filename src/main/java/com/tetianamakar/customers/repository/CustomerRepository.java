package com.tetianamakar.customers.repository;

import com.tetianamakar.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select (count(c) > 0) from Customer c where c.email = :email")
    boolean existsByEmail(@Param("email") String email);


}