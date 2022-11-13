package com.web.ecommerceapp.payload.user;
import lombok.Data;

@Data
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}


