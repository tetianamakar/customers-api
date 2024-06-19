package com.tetianamakar.customers.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;


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
    private String phone;
    private boolean isActive;

    @PrePersist
    protected void onCreate() {
        created = Instant.now().toEpochMilli();
        isActive = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = Instant.now().toEpochMilli();
    }

}
