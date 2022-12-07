package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.Role;
import com.web.ecommerceapp.entity.User;
import com.web.ecommerceapp.exception.ResourceNotFoundException;
import com.web.ecommerceapp.mapper.UserMapper;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.ManagerResponseDto;
import com.web.ecommerceapp.payload.user.OperatorResponseDto;
import com.web.ecommerceapp.payload.user.RegisterDto;
import com.web.ecommerceapp.repository.RoleRepository;
import com.web.ecommerceapp.repository.UserRepository;
import com.web.ecommerceapp.service.ManagerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

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

        userRepository.save(user);

        return new ActionSuccessful(true,"Manager created successfully");
    }

    @Override
    public List<ManagerResponseDto> getAll() {
        List<User> users=userRepository.getUserByRole("ROLE_MANAGER");
        List<ManagerResponseDto> managersList=userMapper.managerToDto(users);
        return managersList;
    }

    @Override
    public ManagerResponseDto getManagerById(Long id) {
        User user=userRepository.findManagerById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        ManagerResponseDto manager=userMapper.managerToDto(user);
        return manager;
    }

    @Override
    public List<OperatorResponseDto> getOperatorsOfManager(Principal principal) {
        User user=userRepository.getUserByEmailOrUsername(principal.getName(), principal.getName());

        List<User> users=userRepository.getUserByManagerId(user.getId());

        List<OperatorResponseDto> operatorList=userMapper.operatorToDto(users);

        return operatorList;
    }


}
