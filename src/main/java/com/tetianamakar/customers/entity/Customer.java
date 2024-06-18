package com.tetianamakar.customers.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long created;
    private Long updated;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    // nullable
    private String phone;
    private boolean isActive;

}
