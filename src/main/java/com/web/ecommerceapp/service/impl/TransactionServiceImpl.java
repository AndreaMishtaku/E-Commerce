package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.Transaction;
import com.web.ecommerceapp.enums.ColumnType;
import com.web.ecommerceapp.mapper.TransactionMapper;
import com.web.ecommerceapp.payload.pagination.ColumnDto;
import com.web.ecommerceapp.payload.pagination.PaginationRequestDto;
import com.web.ecommerceapp.payload.pagination.PaginationResponseDto;
import com.web.ecommerceapp.payload.transaction.TransactionResponseDto;
import com.web.ecommerceapp.repository.TransactionRepository;
import com.web.ecommerceapp.service.TransactionService;
import com.web.ecommerceapp.utils.PaginationSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;
    TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository,TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }


    @Override
    public List<TransactionResponseDto> getAllTransactions() {

        List<Transaction> transactions=transactionRepository.findAll();
        List<TransactionResponseDto> transactionsResponse=transactionMapper.transactionToDto(transactions);

        return transactionsResponse;
    }

    @Override
    public PaginationResponseDto<TransactionResponseDto> getAllTransactionsWithPagination(PaginationRequestDto paginationRequestDto) {

        PaginationSpecification<Transaction> specification = new PaginationSpecification<>(paginationRequestDto);
        Pageable pageable = specification.getPageable(paginationRequestDto.getPage(), paginationRequestDto.getSize());

        Page<Transaction> transactions=transactionRepository.findAll(specification, pageable);

        List<TransactionResponseDto> transactionResponseDtos=transactionMapper.transactionToDto(transactions.getContent());

        return getTransactionsWithPagination(transactions,transactionResponseDtos);
    }


    private PaginationResponseDto<TransactionResponseDto> getTransactionsWithPagination(Page<Transaction> page, List<TransactionResponseDto> transactions) {
        List<ColumnDto> columns = new ArrayList<>();
        columns.add(new ColumnDto("Id","id", ColumnType.NUMBER,false,false,false,true));
        columns.add(new ColumnDto("Description","description", ColumnType.STRING,true,true,true,false));
        columns.add(new ColumnDto("Amount","amount", ColumnType.NUMBER,true,true,true,false));
        columns.add(new ColumnDto("Bank Card","bankCard", ColumnType.NUMBER,true,true,true,false));
        columns.add(new ColumnDto("Date","date", ColumnType.NUMBER,true,true,true,false));


        return new PaginationResponseDto<>(transactions, page, columns);
    }

}
