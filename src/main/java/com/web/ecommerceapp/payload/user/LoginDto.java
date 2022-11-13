package com.web.ecommerceapp.payload.user;
import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
