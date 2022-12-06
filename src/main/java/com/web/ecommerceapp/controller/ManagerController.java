package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.RegisterDto;
import com.web.ecommerceapp.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Manager Controller", tags = {"Manager"},description = "Manager Controller")
@SwaggerDefinition(tags = {@Tag(name = "Manager") })
@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @ApiOperation(value = "Register manager")
    @PostMapping
    public ResponseEntity<ActionSuccessful> registerManager(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(managerService.registerManager(registerDto), HttpStatus.CREATED);
    }

}
