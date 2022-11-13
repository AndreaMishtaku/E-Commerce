package com.web.ecommerceapp.mapper;
import com.web.ecommerceapp.entity.BankAccount;
import com.web.ecommerceapp.payload.bankaccount.BankAccountRequestDto;
import com.web.ecommerceapp.payload.bankaccount.BankAccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    List<BankAccountResponseDto> bankAccountToDto(List<BankAccount> bankAccounts);

    @Mapping(source = "clientFirstName",target = "client.firstName")
    @Mapping(source = "clientLastName",target = "client.lastName")
    @Mapping(source="clientAge",target = "client.age")
    @Mapping(source = "clientEmail",target = "client.email")
    BankAccount dtoToBankAccount(BankAccountRequestDto bankAccountDto);

    BankAccountResponseDto bankAccountToDto(BankAccount bankAccount);


    @Mapping(source = "clientFirstName",target = "client.firstName")
    @Mapping(source = "clientLastName",target = "client.lastName")
    @Mapping(source="clientAge",target = "client.age")
    @Mapping(source = "clientEmail",target = "client.email")
    void update( BankAccountRequestDto bankDto,@MappingTarget BankAccount bankAccount);
}
