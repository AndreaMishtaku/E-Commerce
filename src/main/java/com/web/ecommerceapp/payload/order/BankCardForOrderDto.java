package com.web.ecommerceapp.payload.order;

import lombok.Data;

@Data
public class BankCardForOrderDto {
    private String code;
    private String password;
}
