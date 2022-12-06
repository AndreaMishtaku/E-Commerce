package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.OperatorRegisterDto;
import com.web.ecommerceapp.payload.user.RegisterDto;
import com.web.ecommerceapp.service.ManagerService;
import com.web.ecommerceapp.service.OperatorService;
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

@Api(value = "Operator Controller", tags = {"Operator"},description = "Operator Controller")
@SwaggerDefinition(tags = {@Tag(name = "Operator") })
@RestController
@RequestMapping("/api/operator")
public class OperatorController {

    private OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService=operatorService;
    }

    @ApiOperation(value = "Register operator")
    @PostMapping
    public ResponseEntity<ActionSuccessful> registerOperator(@RequestBody OperatorRegisterDto operatorRegisterDto){
        return new ResponseEntity<>(operatorService.registerOperator(operatorRegisterDto), HttpStatus.CREATED);
    }

}
