package com.epam.car_rental.service.info;

import com.epam.car_rental.service.ServiceException;

public class ServiceNotFoundException extends ServiceException {
    public ServiceNotFoundException(String massage){
        super(massage);
    }
}
