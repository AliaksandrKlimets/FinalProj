package com.epam.car_rental.service;

public class DateNotAvailableServiceException extends ServiceException {
    public DateNotAvailableServiceException(String massage){
        super(massage);
    }
}
