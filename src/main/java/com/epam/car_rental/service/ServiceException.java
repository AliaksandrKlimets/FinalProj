package com.epam.car_rental.service;

import com.epam.car_rental.entity.Service;

public class ServiceException extends Exception {
    public ServiceException(String massage){
        super(massage);
    }
}
