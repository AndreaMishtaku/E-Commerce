package com.web.ecommerceapp.payload.user;

import lombok.Data;

@Data
public class OperatorResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long managerId;
}
