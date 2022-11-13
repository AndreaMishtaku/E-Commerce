package com.web.ecommerceapp.payload.bankaccount;

import lombok.Data;

@Data
public class BankAccountRequestDto {
    private String name;
    private String clientFirstName;
    private String clientLastName;
    private String clientAge;
    private String clientEmail;
    private Double balance;
}
