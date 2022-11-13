package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.bankaccount.BankAccountRequestDto;
import com.web.ecommerceapp.payload.bankaccount.BankAccountResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;

public interface BankAccountService {

    ActionSuccessful createBankAccount(BankAccountRequestDto bankAccountDto);

    BankAccountResponseDto getBankAccountById(Long id);

    ActionSuccessful updateBankAccount(BankAccountRequestDto bankAccountDto,Long id);

    ActionSuccessful deleteBankAccount(Long id);


}
