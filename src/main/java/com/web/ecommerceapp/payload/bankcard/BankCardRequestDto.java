package com.web.ecommerceapp.payload.bankcard;

import lombok.Data;


@Data
public class BankCardRequestDto {
    private String code;
    private String password;
    private Long bankAccountId;

}
