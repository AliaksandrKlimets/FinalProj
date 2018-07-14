package com.epam.car_rental.service.user;

import com.epam.car_rental.service.ServiceException;

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException(String massage){
        super(massage);
    }
}
