package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.BankAccount;
import com.web.ecommerceapp.entity.BankCard;
import com.web.ecommerceapp.exception.ResourceNotFoundException;
import com.web.ecommerceapp.mapper.BankCardMapper;
import com.web.ecommerceapp.payload.bankcard.BankCardRequestDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.repository.BankAccountRepository;
import com.web.ecommerceapp.repository.BankCardRepository;
import com.web.ecommerceapp.service.BankCardService;
import com.web.ecommerceapp.utils.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BankCardServiceImpl implements BankCardService {

    private BankCardRepository bankCardRepository;
    private BankAccountRepository bankAccountRepository;

    private PasswordEncoder passwordEncoder;
    private BankCardMapper bankCardMapper;

    public BankCardServiceImpl(BankCardRepository bankCardRepository, BankAccountRepository bankAccountRepository, PasswordEncoder passwordEncoder, BankCardMapper bankCardMapper) {
        this.bankCardRepository = bankCardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.bankCardMapper= bankCardMapper;
    }

    @Override
    public ActionSuccessful createBankCard(BankCardRequestDto bankCardDto) {
        BankCard bankCard=bankCardMapper.dtoToBankCard(bankCardDto);
        bankCard.setPassword(passwordEncoder.encode(bankCardDto.getPassword()));
        bankCard.setExpiryDate(LocalDate.now().plusYears(5));
        bankCardRepository.save(bankCard);
        return new ActionSuccessful(true,"Bank Card created successfully");
    }

    @Override
    public ActionSuccessful deleteBankCard(Long id) {
        BankCard bankCard=bankCardRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Bank Card","id",id));
        bankCardRepository.delete(bankCard);
        return new ActionSuccessful(true,"Bank deleted successfully");
    }
}
