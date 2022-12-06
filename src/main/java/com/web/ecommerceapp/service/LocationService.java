package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.location.LocationRequestDto;
import com.web.ecommerceapp.payload.location.LocationResponseDto;
import com.web.ecommerceapp.payload.location.ProductLocationRequestDto;
import com.web.ecommerceapp.payload.location.ProductLocationResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;

import java.security.Principal;
import java.util.List;

public interface LocationService {

    ActionSuccessful createLocation(LocationRequestDto locationDto);

    List<LocationResponseDto> getAllLocations();

    LocationResponseDto getLocationById(Principal principal,Long id);

    ActionSuccessful updateLocation(LocationRequestDto locationDto,Long id);

    ActionSuccessful addNewProduct(ProductLocationRequestDto productLocationDto, Long locationId);

    ActionSuccessful removeProduct(ProductLocationRequestDto productLocationDto, Long locationId);

    ActionSuccessful deleteLocation(Long id);

    List<ProductLocationResponseDto> getLocationProducts(Principal principal,Long id);
}
