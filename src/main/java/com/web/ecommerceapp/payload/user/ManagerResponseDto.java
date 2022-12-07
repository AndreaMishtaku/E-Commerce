package com.web.ecommerceapp.payload.user;


import lombok.Data;

@Data
public class ManagerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String locationName;
}
