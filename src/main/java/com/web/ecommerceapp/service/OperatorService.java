package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.OperatorRegisterDto;

public interface OperatorService {
    ActionSuccessful registerOperator(OperatorRegisterDto operatorRegisterDto);
}
