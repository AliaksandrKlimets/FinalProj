package com.epam.car_rental.service.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String massage){
        super(massage);
    }
}
