package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.bankaccount.BankAccountRequestDto;
import com.web.ecommerceapp.payload.bankaccount.BankAccountResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.BankAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "BankAccount Controller", tags = {"BankAccount"},description = "BankAccount Controller")
@SwaggerDefinition(tags = {@Tag(name = "BankAccount") })
@RestController
@RequestMapping("/api/bankAccounts")
public class BankAccountController {

    BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @PostMapping
    public ResponseEntity<ActionSuccessful> createBankAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto){
        return new ResponseEntity<>(bankAccountService.createBankAccount(bankAccountRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponseDto>  getBankAccountById(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(bankAccountService.getBankAccountById(id),HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ActionSuccessful>  updateBankAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto,@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(bankAccountService.updateBankAccount(bankAccountRequestDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteBankAccount(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(bankAccountService.deleteBankAccount(id),HttpStatus.OK);
    }
}
