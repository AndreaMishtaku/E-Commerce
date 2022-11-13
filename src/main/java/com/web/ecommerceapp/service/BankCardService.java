package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.bankcard.BankCardRequestDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;

public interface BankCardService {
    ActionSuccessful createBankCard(BankCardRequestDto bankCardDto);
    ActionSuccessful deleteBankCard(Long id);
}
