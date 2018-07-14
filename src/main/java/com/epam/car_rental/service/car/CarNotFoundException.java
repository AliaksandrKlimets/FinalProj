package com.epam.car_rental.service.car;

import com.epam.car_rental.service.ServiceException;

public class CarNotFoundException extends ServiceException {
    public CarNotFoundException(String massage){
        super(massage);
    }
}
