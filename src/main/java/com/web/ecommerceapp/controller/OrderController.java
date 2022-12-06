package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.order.OrderRequestDto;
import com.web.ecommerceapp.payload.order.OrderResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
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
@Api(value = "Order Controller", tags = {"Order"},description = "Order Controller")
@SwaggerDefinition(tags = {@Tag(name = "Order") })
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Create Order **")
    @PostMapping
    public ResponseEntity<ActionSuccessful> createOrder(@RequestBody OrderRequestDto orderDto, Principal principal){
        return new ResponseEntity<>(orderService.createOrder(orderDto,principal), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get all orders ADMIN")
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }

    @ApiOperation(value ="Get order by order id  OPERATOR|MANAGER|ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable(name = "id") long id,Principal principal){
        return new ResponseEntity<>(orderService.getOrderById(id,principal),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('MANAGER')")
    @ApiOperation(value ="Delete order by order id  ADMIN" )
    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteOrderById(@PathVariable(name = "id") long id,Principal principal){
        return new ResponseEntity<>(orderService.deleteOrderById(id,principal),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get all online orders  ADMIN")
    @GetMapping("/online")
    public ResponseEntity<List<OrderResponseDto>> getOnlineOrders(){
        return new ResponseEntity<>(orderService.getAllOnlineOrders(),HttpStatus.OK);
    }
}
