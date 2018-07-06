package com.epam.car_rental.service.user;

public class UserExistException extends Exception {
    public UserExistException(String massage){
        super(massage);
    }
}
