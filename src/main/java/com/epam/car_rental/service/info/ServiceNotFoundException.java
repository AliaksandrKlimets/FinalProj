package com.epam.car_rental.service.info;

public class ServiceNotFoundException extends Exception {
    public ServiceNotFoundException(String massage){
        super(massage);
    }
}
