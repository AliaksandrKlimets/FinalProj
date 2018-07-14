package com.epam.car_rental.service.validation;

import com.epam.car_rental.service.ServiceException;

public class InvalidParametersException extends ServiceException {
    public InvalidParametersException(String massage){
        super(massage);
    }
}
