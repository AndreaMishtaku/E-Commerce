package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.order.OrderResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.OperatorRegisterDto;
import com.web.ecommerceapp.payload.user.RegisterDto;
import com.web.ecommerceapp.service.ManagerService;
import com.web.ecommerceapp.service.OperatorService;
import com.web.ecommerceapp.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Api(value = "Operator Controller", tags = {"Operator"},description = "Operator Controller")
@SwaggerDefinition(tags = {@Tag(name = "Operator") })
@RestController
@RequestMapping("/api/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;
    @Autowired
    private OrderService orderService;
    public OperatorController(OperatorService operatorService) {
        this.operatorService=operatorService;
    }

    @ApiOperation(value = "Register operator MANAGER")
    @PostMapping
    public ResponseEntity<ActionSuccessful> registerOperator(@RequestBody OperatorRegisterDto operatorRegisterDto){
        return new ResponseEntity<>(operatorService.registerOperator(operatorRegisterDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('OPERATOR')")
    @ApiOperation(value = "Get orders of operator")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getOperatorOrders(Principal principal){
        return new ResponseEntity<>(orderService.getAllOrdersOfOperator(principal),HttpStatus.OK);
    }

}
