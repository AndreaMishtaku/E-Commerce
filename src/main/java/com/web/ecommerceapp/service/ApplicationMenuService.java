package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.menu.MenuDto;

import java.security.Principal;
import java.util.List;

public interface ApplicationMenuService {
    List<MenuDto> getApplicationMenu(Principal principal);
}
