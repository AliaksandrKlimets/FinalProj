package com.epam.car_rental.controller.command;

public class AccessNotAllowedException extends Exception {
    public AccessNotAllowedException(String massage){
        super(massage);
    }
}
