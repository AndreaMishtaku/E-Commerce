package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.RegisterDto;

public interface ManagerService {
    ActionSuccessful registerManager(RegisterDto registerDto);
}
