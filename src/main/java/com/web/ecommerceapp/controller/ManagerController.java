package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.order.OrderResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.payload.user.RegisterDto;
import com.web.ecommerceapp.service.ManagerService;
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

@Api(value = "Manager Controller", tags = {"Manager"},description = "Manager Controller")
@SwaggerDefinition(tags = {@Tag(name = "Manager") })
@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private OrderService orderService;


    @ApiOperation(value = "Register manager")
    @PostMapping
    public ResponseEntity<ActionSuccessful> registerManager(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(managerService.registerManager(registerDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Manager')")
    @ApiOperation(value = "Get orders of manager")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getOperatorOrders(Principal principal){
        return new ResponseEntity<>(orderService.getAllOrdersOfManager(principal),HttpStatus.OK);
    }

}
