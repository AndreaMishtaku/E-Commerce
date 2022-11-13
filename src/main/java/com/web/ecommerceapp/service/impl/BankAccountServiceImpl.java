package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.BankAccount;
import com.web.ecommerceapp.exception.ResourceNotFoundException;
import com.web.ecommerceapp.mapper.BankAccountMapper;
import com.web.ecommerceapp.payload.bankaccount.BankAccountRequestDto;
import com.web.ecommerceapp.payload.bankaccount.BankAccountResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.repository.BankAccountRepository;
import com.web.ecommerceapp.service.BankAccountService;
import com.web.ecommerceapp.utils.Mapper;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private BankAccountMapper bankAccountMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public ActionSuccessful createBankAccount(BankAccountRequestDto bankAccountDto) {
        BankAccount bankAccount=bankAccountMapper.dtoToBankAccount(bankAccountDto);
        bankAccountRepository.save(bankAccount);

        return new ActionSuccessful(true,"Bank created successfully");
    }

    @Override
    public BankAccountResponseDto getBankAccountById(Long id) {
        BankAccount bankAccount=bankAccountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Bank Account","id",id));
        BankAccountResponseDto bankAccountResponse=bankAccountMapper.bankAccountToDto(bankAccount);
        return bankAccountResponse;
    }

    @Override
    public ActionSuccessful updateBankAccount(BankAccountRequestDto bankAccountDto, Long id) {
        BankAccount bankAccount=bankAccountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("BankAccount","id",id));
        bankAccountMapper.update(bankAccountDto,bankAccount);

        bankAccountRepository.save(bankAccount);
        return new ActionSuccessful(true,"Bank updated successfully");
    }

    @Override
    public ActionSuccessful deleteBankAccount(Long id) {
        BankAccount bankAccount=bankAccountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("BankAccount","id",id));
        bankAccountRepository.delete(bankAccount);
        return new ActionSuccessful(true,"Bank account deleted successfully");
    }
}
