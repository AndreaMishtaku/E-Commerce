package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.ManagerResponseDto;
import com.web.ecommerceapp.payload.user.OperatorResponseDto;
import com.web.ecommerceapp.payload.user.RegisterDto;

import java.security.Principal;
import java.util.List;

public interface ManagerService {
    ActionSuccessful registerManager(RegisterDto registerDto);

    List<ManagerResponseDto> getAll();

    ManagerResponseDto getManagerById(Long id);

    List<OperatorResponseDto> getOperatorsOfManager(Principal principal);

}
