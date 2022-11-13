package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.order.OrderRequestDto;
import com.web.ecommerceapp.payload.order.OrderResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@Api(value = "Order Controller", tags = {"Order"},description = "Order Controller")
@SwaggerDefinition(tags = {@Tag(name = "Order") })
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService= orderService;
    }

    @ApiOperation(value = "Create Order")
    @PostMapping
    public ResponseEntity<ActionSuccessful> createProduct(@RequestBody OrderRequestDto orderDto, Principal principal){
        return new ResponseEntity<>(orderService.createOrder(orderDto,principal), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get orders by client id")
    @GetMapping("/user")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUser(Principal principal){
        return new ResponseEntity<>(orderService.getAllOrdersUser(principal),HttpStatus.OK);
    }

    @ApiOperation(value = "Get all orders")
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }

    @ApiOperation(value ="Get order by order id" )
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable(name = "id") long id,Principal principal){
        return new ResponseEntity<>(orderService.getOrderById(id,principal),HttpStatus.OK);
    }

    @ApiOperation(value ="Get order by order id" )
    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteOrderById(@PathVariable(name = "id") long id,Principal principal){
        return new ResponseEntity<>(orderService.deleteOrderById(id,principal),HttpStatus.OK);
    }
}
