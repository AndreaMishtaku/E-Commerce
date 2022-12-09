package com.web.ecommerceapp.mapper;


import com.web.ecommerceapp.entity.ApplicationMenu;
import com.web.ecommerceapp.payload.menu.BaseMenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicationMenuMapper {
    ApplicationMenuMapper INSTANCE = Mappers.getMapper(ApplicationMenuMapper.class);

    BaseMenuDto menuToBaseMenuDto(ApplicationMenu menu);
}
