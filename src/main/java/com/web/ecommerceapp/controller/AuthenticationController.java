package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.entity.RefreshToken;
import com.web.ecommerceapp.entity.Role;
import com.web.ecommerceapp.entity.User;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.LoginDto;
import com.web.ecommerceapp.payload.user.RegisterDto;
import com.web.ecommerceapp.payload.user.TokenDto;
import com.web.ecommerceapp.repository.RoleRepository;
import com.web.ecommerceapp.repository.UserRepository;
import com.web.ecommerceapp.security.JwtUtils;
import com.web.ecommerceapp.service.impl.RefreshTokenService;
import com.web.ecommerceapp.service.impl.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
@Api(value = "Sign in and sign up users", tags = {"Authentication"},description = "Sign in and sign up users")
@SwaggerDefinition(tags = {@Tag(name = "Authentication") })
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @ApiOperation(value = "User log in **")
    @PostMapping("/signin")
    public ResponseEntity<TokenDto> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        TokenDto tokenDto = new TokenDto(jwt,refreshToken.getToken());

        return ResponseEntity.ok(tokenDto);
    }

    @ApiOperation(value = "User register as MANAGER **")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        if(userRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());

        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_MANAGER").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>(new ActionSuccessful(true,"User registered successfully"), HttpStatus.OK);
    }


    @ApiOperation(value = "Refresh token")
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenDto> refreshToken(@RequestBody TokenDto tokenDto) {
        String requestRefreshToken = tokenDto.getRefreshToken();

       // RefreshToken refreshToken=refreshTokenService.getByToken(requestRefreshToken);
//
//
//
       // refreshTokenService.verifyExpiration(refreshToken);
//
       // User user=refreshToken.getUser();
//
       // String newToken=jwtUtils.generateTokenFromUsername(user.getUsername());
       // RefreshToken newRefreshToken=refreshTokenService.generateNewRefreshToken(user);
//
       // TokenDto tokenDtoResponse = new TokenDto(newToken,newRefreshToken.getToken());
//
       // return ResponseEntity.ok(tokenDtoResponse);
        return null;
    }
}
