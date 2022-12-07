package com.web.ecommerceapp.mapper;


import com.web.ecommerceapp.entity.Product;
import com.web.ecommerceapp.entity.User;
import com.web.ecommerceapp.payload.product.ProductResponseDto;
import com.web.ecommerceapp.payload.user.ManagerResponseDto;
import com.web.ecommerceapp.payload.user.OperatorRegisterDto;
import com.web.ecommerceapp.payload.user.OperatorResponseDto;
import com.web.ecommerceapp.payload.user.RegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ReferenceMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "managerId",target = "manager")
    User operatorDtoToEntity(OperatorRegisterDto operatorDto);
    @Mapping(target = "password", ignore = true)
    User dtoToEntity(RegisterDto registerDto);

    @Mapping(source = "location.name",target = "locationName")
    ManagerResponseDto managerToDto(User user);

    List<ManagerResponseDto> managerToDto(List<User> users);

    @Mapping(source = "manager",target = "managerId")
    OperatorResponseDto operatorToDto(User user);
    List<OperatorResponseDto> operatorToDto(List<User> users);
}
