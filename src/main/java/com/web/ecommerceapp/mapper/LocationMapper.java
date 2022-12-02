package com.web.ecommerceapp.mapper;

import com.web.ecommerceapp.entity.Location;
import com.web.ecommerceapp.entity.ProductLocation;
import com.web.ecommerceapp.payload.location.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ReferenceMapper.class})
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    List<LocationResponseDto> locationToDto(List<Location> locations);

    @Mapping(source = "userId",target = "user")
    Location dtoToLocation(LocationRequestDto locationDto);

    @Mapping(source ="user",target = "userId")
    LocationResponseDto locationToDto(Location location);

    @Mapping(source = "product.name",target = "productName")
    ProductLocationResponseDto productLocationtoDto(ProductLocation productLocation);

    @Mapping(source = "productId",target = "product")
    @Mapping(source = "stock",target ="stock" )
    ProductLocation dtoToProductLocation(ProductLocationRequestDto productLocationDto);

    List<ProductLocationResponseDto> productLocationToDto(List<ProductLocation> productLocation);
    @Mapping(source = "userId",target = "user")
    void update(LocationRequestDto locationDto,@MappingTarget Location Location);

}
