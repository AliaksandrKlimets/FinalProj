package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;

public class DateNotAvailableException extends DAOException {
    public DateNotAvailableException(String massage){
        super(massage);
    }
}
