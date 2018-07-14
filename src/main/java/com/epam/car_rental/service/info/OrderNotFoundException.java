package com.epam.car_rental.service.info;

import com.epam.car_rental.service.ServiceException;

public class OrderNotFoundException extends ServiceException {
    public OrderNotFoundException(String massage){
        super(massage);
    }
}
