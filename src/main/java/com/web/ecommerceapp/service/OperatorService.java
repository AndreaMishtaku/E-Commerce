package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.OperatorRegisterDto;
import com.web.ecommerceapp.payload.user.OperatorResponseDto;

import java.util.List;

public interface OperatorService {
    ActionSuccessful registerOperator(OperatorRegisterDto operatorRegisterDto);

    List<OperatorResponseDto> getAll();

    OperatorResponseDto getOperatorById(Long id);
}
