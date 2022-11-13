package com.web.ecommerceapp.mapper;

import com.web.ecommerceapp.entity.Order;
import com.web.ecommerceapp.entity.OrderProduct;
import com.web.ecommerceapp.payload.order.OrderProductResponseDto;

import com.web.ecommerceapp.payload.order.OrderRequestDto;
import com.web.ecommerceapp.payload.order.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring",uses = {ReferenceMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    List<OrderProductResponseDto> orderProductToDto(List<OrderProduct> orderProducts);

    @Mapping(source = "product.name",target = "productName")
    OrderProductResponseDto orderProductToDto(OrderProduct orderProduct);


    List<OrderResponseDto> orderToDto(List<Order> orders);


    @Mapping(source = "location",target = "locationId")
    @Mapping(source = "user",target = "userId")
    @Mapping(source = "bankCard.code",target = "bankCardCode")
    @Mapping(source = "amount",target = "amount")
    OrderResponseDto orderToDto(Order order);

}
