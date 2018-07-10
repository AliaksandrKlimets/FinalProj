package com.epam.car_rental.service.validation;

public class NotDateException extends Exception {
    public NotDateException(String massage){
        super(massage);
    }
}
