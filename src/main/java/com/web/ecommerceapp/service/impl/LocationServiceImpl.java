package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.*;
import com.web.ecommerceapp.exception.ResourceNotFoundException;
import com.web.ecommerceapp.mapper.LocationMapper;
import com.web.ecommerceapp.payload.location.*;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.repository.LocationRepository;
import com.web.ecommerceapp.repository.ProductLocationRepository;
import com.web.ecommerceapp.repository.ProductRepository;
import com.web.ecommerceapp.repository.UserRepository;
import com.web.ecommerceapp.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    private ProductLocationRepository productLocationRepository;
    private LocationMapper locationMapper;


    public LocationServiceImpl(LocationRepository locationRepository, ProductRepository productRepository, UserRepository userRepository, ProductLocationRepository productLocationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productLocationRepository = productLocationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public ActionSuccessful createLocation(LocationRequestDto locationDto) {
        User user=userRepository.findById(locationDto.getUserId()).orElseThrow(()->new ResourceNotFoundException("Location","id",locationDto.getUserId()));

        for (Role role :
                user.getRoles()
                ) {
            if(role.getName()=="ROLE_OPERATOR"||role.getName()=="ROLE_ADMIN"){
                throw new RuntimeException("Location cannot set to this user");
            }

        }
        Location location=locationMapper.dtoToLocation(locationDto);

        locationRepository.save(location);

        return new ActionSuccessful(true,"Location created successfully");
    }

    @Override
    public List<LocationResponseDto> getAllLocations() {

        List<Location> locations=locationRepository.findAll();

        List<LocationResponseDto> locationsList=locationMapper.locationToDto(locations);

        return locationsList;
    }

    @Override
    public LocationResponseDto getLocationById(Long id) {
        Location location=locationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Location","id",id));
        LocationResponseDto locationResponse=locationMapper.locationToDto(location);

        return locationResponse;
    }

    @Override
    public List<ProductLocationResponseDto> getLocationProducts(Long id) {
        List<ProductLocation> products=productLocationRepository.findByLocation_id(id);
        List<ProductLocationResponseDto> productList=locationMapper.productLocationToDto(products);
        return productList;
    }

    @Override
    public ActionSuccessful updateLocation(LocationRequestDto locationDto,Long id) {
        Location location=locationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Location","id",id));
        locationMapper.update(locationDto,location);
        locationRepository.save(location);

        return new ActionSuccessful(true,"Location created successfully");
    }

    @Override
    public ActionSuccessful addNewProduct(ProductLocationRequestDto productLocationDto, Long locationId ) {
        Location location=locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFoundException("Location","id",locationId));


        for (ProductLocation pl:
             location.getProductLocations()) {

            if(pl.getProduct().getId().equals(productLocationDto.getProductId())){
                System.out.println("Hyri ktu");
                pl.setStock(pl.getStock()+productLocationDto.getStock());
                locationRepository.save(location);
                return new ActionSuccessful(true,"Product added successfully to selected location");
            }
        }

        ProductLocation productLocation=locationMapper.dtoToProductLocation(productLocationDto,location);
        location.getProductLocations().add(productLocation);
        locationRepository.save(location);
        return new ActionSuccessful(true,"Product added successfully to selected location");
    }

    @Override
    public ActionSuccessful removeProduct(ProductLocationRequestDto productLocationDto, Long locationId) {
        Location location=locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFoundException("Location","id",locationId));

        for (ProductLocation pl:
                location.getProductLocations()) {

            if(pl.getProduct().getId().equals(productLocationDto.getProductId())){
                if(pl.getStock()<productLocationDto.getStock()){
                    throw new RuntimeException("Sasia qe doni te hiqni eshte me e madhe se stoku");
                }else if(pl.getStock()==productLocationDto.getStock()){
                    location.getProductLocations().remove(pl);
                    locationRepository.save(location);
                    return new ActionSuccessful(true,"Product removed successfully to selected location");
                }
                pl.setStock(pl.getStock()-productLocationDto.getStock());
                locationRepository.save(location);
                return new ActionSuccessful(true,"Product removed successfully to selected location");
            }
        }

        ProductLocation productLocation=locationMapper.dtoToProductLocation(productLocationDto,location);
        location.getProductLocations().add(productLocation);
        locationRepository.save(location);
        return new ActionSuccessful(true,"Product removed successfully to selected location");

    }

    @Override
    public ActionSuccessful deleteLocation(Long id) {
        Location location=locationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Location","id",id));
        locationRepository.delete(location);
        return new ActionSuccessful(true,"Location deleted successfully");
    }
}
