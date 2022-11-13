package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.entity.Transaction;
import com.web.ecommerceapp.payload.pagination.PaginationRequestDto;
import com.web.ecommerceapp.payload.pagination.PaginationResponseDto;
import com.web.ecommerceapp.payload.transaction.TransactionResponseDto;
import com.web.ecommerceapp.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Transaction Controller", tags = {"Transaction"},description = "Transaction Controller")
@SwaggerDefinition(tags = {@Tag(name = "Transaction") })
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "Get all transactions")
    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all transactions")
    @PostMapping("/get-all")
    public ResponseEntity<PaginationResponseDto<TransactionResponseDto>> getAllTransactionsWithPagination(@RequestBody PaginationRequestDto paginationRequestDto){
        return new ResponseEntity<>(transactionService.getAllTransactionsWithPagination(paginationRequestDto), HttpStatus.OK);
    }
}
