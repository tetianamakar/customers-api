package com.tetianamakar.customers.payload.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    @Size(min = 2, max = 50)
    private String fullName;

    @Size(min = 2, max = 100)
    @Pattern(regexp = "^[^@]*@[^@]*$", message = "Email should include only one @")
    private String email;

    @Size(min = 6, max = 14, message = "Phone number must be between 6 and 14 characters")
    @Pattern(regexp = "^\\+\\d{1,13}$", message = "Phone number must start with '+' and be between 1 to 13 digits")
    private String phone;


}
