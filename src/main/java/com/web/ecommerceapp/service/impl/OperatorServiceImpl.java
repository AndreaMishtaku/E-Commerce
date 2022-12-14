package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.Role;
import com.web.ecommerceapp.entity.User;
import com.web.ecommerceapp.exception.ResourceNotFoundException;
import com.web.ecommerceapp.mapper.UserMapper;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.OperatorRegisterDto;
import com.web.ecommerceapp.payload.user.OperatorResponseDto;
import com.web.ecommerceapp.payload.user.RegisterDto;
import com.web.ecommerceapp.repository.RoleRepository;
import com.web.ecommerceapp.repository.UserRepository;
import com.web.ecommerceapp.service.OperatorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    public OperatorServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public ActionSuccessful registerOperator(OperatorRegisterDto operatorRegisterDto){

        User user=userMapper.operatorDtoToEntity(operatorRegisterDto);

        user.setPassword(passwordEncoder.encode(operatorRegisterDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_OPERATOR").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);


        return new ActionSuccessful(true,"Operator created successfully");
    }

    @Override
    public List<OperatorResponseDto> getAll() {
        List<User> users=userRepository.getUserByRole("ROLE_OPERATOR");
        List<OperatorResponseDto> operatorlist=userMapper.operatorToDto(users);

        return operatorlist;
    }

    @Override
    public OperatorResponseDto getOperatorById(Long id) {
        User user=userRepository.findOperatorById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        OperatorResponseDto operator=userMapper.operatorToDto(user);
        return operator;
    }
}
