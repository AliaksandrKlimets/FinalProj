package com.epam.car_rental.service.validation;

public class NotNumberException extends Exception {
    public NotNumberException(String massage){
        super(massage);
    }
}
