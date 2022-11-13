package com.web.ecommerceapp.payload.bankaccount;


import com.web.ecommerceapp.entity.embeddable.Client;
import com.web.ecommerceapp.payload.bankcard.BankCardResponseDto;
import lombok.Data;

@Data
public class BankAccountResponseDto {
    private Long id;
    private Client client;
    private Double balance;
    private BankCardResponseDto bankCard;
}
