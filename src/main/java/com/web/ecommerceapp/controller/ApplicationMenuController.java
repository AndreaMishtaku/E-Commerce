package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.menu.MenuDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.ApplicationMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@Api(value = "Application Menu Controller", tags = {"Application Menu"},description = "Application Menu Controller")
@SwaggerDefinition(tags = {@Tag(name = "Application Menu") })
@RestController
@RequestMapping("/api/menu")
public class ApplicationMenuController {


    @Autowired
    ApplicationMenuService applicationMenuService;

    @ApiOperation(value = "Get application menu")
    @GetMapping
    public ResponseEntity<List<MenuDto>> getApplicationMenu(Principal principal){
        return new ResponseEntity<>(applicationMenuService.getApplicationMenu(principal), HttpStatus.OK);
    }

}
