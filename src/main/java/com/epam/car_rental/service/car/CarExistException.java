package com.epam.car_rental.service.car;

import com.epam.car_rental.service.ServiceException;

public class CarExistException extends ServiceException {
    public CarExistException(String massage){
        super(massage);
    }
}
