package com.web.ecommerceapp.mapper;

import com.web.ecommerceapp.entity.Transaction;
import com.web.ecommerceapp.payload.transaction.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    List<TransactionResponseDto> transactionToDto(List<Transaction> transactions);

    @Mapping(source = "bankCard.code",target = "bankCardCode")
    TransactionResponseDto transactionToDto(Transaction transaction);
}
