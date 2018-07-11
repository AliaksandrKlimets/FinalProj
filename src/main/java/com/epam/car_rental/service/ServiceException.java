package com.epam.car_rental.service;


public class ServiceException extends Exception {
    public ServiceException(String massage){
        super(massage);
    }
}
