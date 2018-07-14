package com.epam.car_rental.service.validation;

public class NotDateException extends InputException {
    public NotDateException(String massage){
        super(massage);
    }
}
