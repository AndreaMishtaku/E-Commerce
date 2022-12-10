package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.Role;
import com.web.ecommerceapp.entity.User;
import com.web.ecommerceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmailOrUsername(usernameOrEmail,usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));

        return UserDetailsImpl.build(user);
    }


}

