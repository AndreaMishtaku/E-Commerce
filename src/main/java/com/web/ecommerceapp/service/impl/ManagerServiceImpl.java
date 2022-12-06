package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.Role;
import com.web.ecommerceapp.entity.User;
import com.web.ecommerceapp.mapper.UserMapper;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.RegisterDto;
import com.web.ecommerceapp.repository.RoleRepository;
import com.web.ecommerceapp.repository.UserRepository;
import com.web.ecommerceapp.service.ManagerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ManagerServiceImpl implements ManagerService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    public ManagerServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public ActionSuccessful  registerManager(RegisterDto registerDto){

        User user=userMapper.dtoToEntity(registerDto);

        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_MANAGER").get();
        user.setRoles(Collections.singleton(roles));


        return new ActionSuccessful(true,"Manager created successfully");
    }
}
