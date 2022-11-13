package com.web.ecommerceapp.payload.bankcard;
import lombok.Data;

@Data
public class BankCardResponseDto {
    private Long id;


    private String code;

    private String password;

    private String bankAccountId;

    private String expiryDate;

    private String created_at;

    private String  updated_at;

}
