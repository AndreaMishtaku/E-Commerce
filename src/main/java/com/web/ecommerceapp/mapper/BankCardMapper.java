package com.web.ecommerceapp.mapper;

import com.web.ecommerceapp.entity.BankCard;
import com.web.ecommerceapp.payload.bankcard.BankCardRequestDto;
import com.web.ecommerceapp.payload.bankcard.BankCardResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses={ReferenceMapper.class})
public interface BankCardMapper {

    BankCardMapper INSTANCE = Mappers.getMapper(BankCardMapper.class);

    List<BankCardResponseDto> bankCardToDto(List<BankCard> bankCards);

    @Mapping(source = "bankAccountId",target = "bankAccount")
    BankCard dtoToBankCard(BankCardRequestDto bankCardDto);

    @Mapping(source = "bankAccount",target = "bankAccountId")
    BankCardResponseDto bankCardToDto(BankCard bankCard);

    void update( BankCardRequestDto bankCardDto,@MappingTarget BankCard bankCard);
}
