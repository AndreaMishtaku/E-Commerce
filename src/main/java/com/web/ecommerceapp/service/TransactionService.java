package com.web.ecommerceapp.service;

import com.web.ecommerceapp.entity.Transaction;
import com.web.ecommerceapp.payload.pagination.PaginationRequestDto;
import com.web.ecommerceapp.payload.pagination.PaginationResponseDto;
import com.web.ecommerceapp.payload.transaction.TransactionResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    List<TransactionResponseDto> getAllTransactions();

    PaginationResponseDto<TransactionResponseDto> getAllTransactionsWithPagination(PaginationRequestDto paginationRequestDto);
}
