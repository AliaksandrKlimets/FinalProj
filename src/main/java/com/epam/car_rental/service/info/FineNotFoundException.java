package com.epam.car_rental.service.info;

import com.epam.car_rental.service.ServiceException;

public class FineNotFoundException extends ServiceException {
    public FineNotFoundException(String massage){
        super(massage);
    }
}
