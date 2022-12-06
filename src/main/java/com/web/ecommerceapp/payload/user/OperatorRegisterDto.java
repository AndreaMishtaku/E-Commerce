package com.web.ecommerceapp.payload.user;

import lombok.Data;

@Data
public class OperatorRegisterDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Long managerId;

}
