package com.web.ecommerceapp.payload.transaction;

import lombok.Data;

@Data
public class TransactionResponseDto {
    private Long id;
    private String description;
    private Double amount;
    private String bankCardCode;
    private String date;
}
