package com.epam.car_rental.service.validation;

public class InvalidParametersException extends Exception {
    public InvalidParametersException(String massage){
        super(massage);
    }
}
