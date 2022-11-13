package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.bankcard.BankCardRequestDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.BankCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "BankCard Controller", tags = {"BankCard"},description = "BankCard Controller")
@SwaggerDefinition(tags = {@Tag(name = "BankCard") })
@RestController
@RequestMapping("/api/bankCards")
public class BankCardController {

    private BankCardService bankCardService;

    public BankCardController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    @ApiOperation(value = "Create BankAccount")
    @PostMapping
    public ResponseEntity<ActionSuccessful> createBankAccount(@RequestBody BankCardRequestDto bankCardRequestDto){
        return new ResponseEntity<>(bankCardService.createBankCard(bankCardRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete BankAccount")
    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteBankAccount(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(bankCardService.deleteBankCard(id),HttpStatus.OK);
    }
}
