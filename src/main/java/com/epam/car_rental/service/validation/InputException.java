package com.epam.car_rental.service.validation;

public class InputException extends Exception{
    public InputException(String massage){
        super(massage);
    }
}
