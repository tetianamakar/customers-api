package com.tetianamakar.customers.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
}
