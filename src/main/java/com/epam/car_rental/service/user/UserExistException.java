package com.epam.car_rental.service.user;

import com.epam.car_rental.service.ServiceException;

public class UserExistException extends ServiceException {
    public UserExistException(String massage){
        super(massage);
    }
}
