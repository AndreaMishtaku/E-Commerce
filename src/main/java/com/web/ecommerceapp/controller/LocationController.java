package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.location.LocationRequestDto;
import com.web.ecommerceapp.payload.location.LocationResponseDto;
import com.web.ecommerceapp.payload.location.ProductLocationRequestDto;
import com.web.ecommerceapp.payload.location.ProductLocationResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "Location Controller", tags = {"Location"},description = "Location Controller")
@SwaggerDefinition(tags = {@Tag(name = "Location") })
@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @ApiOperation(value = "Create location")
    @PostMapping
    public ResponseEntity<ActionSuccessful> createLocation(@RequestBody LocationRequestDto locationRequestDto){
        return new ResponseEntity<>(locationService.createLocation(locationRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all locations")
    @GetMapping
    public ResponseEntity<List<LocationResponseDto>> getAllLocations(){
        return new ResponseEntity<>(locationService.getAllLocations(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get product by id")
    public ResponseEntity<LocationResponseDto> getProductById(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(locationService.getLocationById(id),HttpStatus.OK);
    }
    @GetMapping("/{id}/products")
    @ApiOperation(value = "Get products")
    public ResponseEntity<List<ProductLocationResponseDto>> getProducts(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(locationService.getLocationProducts(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update location")
    public ResponseEntity<ActionSuccessful> updateProduct(@RequestBody LocationRequestDto productRequestDto,@PathVariable(name="id") Long id){
        return new ResponseEntity<>(locationService.updateLocation(productRequestDto,id),HttpStatus.OK);
    }

    @PostMapping("/{locationId}")
    @ApiOperation(value = "Add product to location")
    public ResponseEntity<ActionSuccessful> addProduct(@RequestBody ProductLocationRequestDto locationAddProductDto, @PathVariable(name="locationId") Long locationId){
        return new ResponseEntity<>(locationService.addNewProduct(locationAddProductDto,locationId),HttpStatus.OK);
    }

    @PostMapping("/{locationId}/deleteProduct")
    @ApiOperation(value = "Remove product from location")
    public ResponseEntity<ActionSuccessful> removeProduct(@RequestBody ProductLocationRequestDto productLocationDto, @PathVariable(name="locationId") Long locationId){
        return new ResponseEntity<>(locationService.removeProduct(productLocationDto,locationId),HttpStatus.OK);
    }

    @ApiOperation(value = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteProduct(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(locationService.deleteLocation(id),HttpStatus.OK);
    }
}