package com.web.ecommerceapp.mapper;


import com.web.ecommerceapp.entity.User;
import com.web.ecommerceapp.payload.user.OperatorRegisterDto;
import com.web.ecommerceapp.payload.user.RegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {ReferenceMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "managerId",target = "manager")
    User operatorDtoToEntity(OperatorRegisterDto operatorDto);
    @Mapping(target = "password", ignore = true)
    User dtoToEntity(RegisterDto registerDto);
    RegisterDto entityToUser(User user);
}
