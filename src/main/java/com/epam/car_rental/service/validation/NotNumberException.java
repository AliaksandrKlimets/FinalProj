package com.epam.car_rental.service.validation;

public class NotNumberException extends InputException {
    public NotNumberException(String massage){
        super(massage);
    }
}
