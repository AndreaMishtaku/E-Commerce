package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.order.OrderRequestDto;
import com.web.ecommerceapp.payload.order.OrderResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    ActionSuccessful createOrder(OrderRequestDto orderDto, Principal principal);

    List<OrderResponseDto> getAllOrdersOfOperator(Principal principal);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderById(Long id,Principal principal);


    ActionSuccessful deleteOrderById(Long id,Principal principal);

    List<OrderResponseDto> getAllOnlineOrders();
}
