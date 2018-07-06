package com.epam.car_rental.dao;

import com.epam.car_rental.DAOException;

public class EntityExistException extends DAOException {
    public EntityExistException(String massage){
        super(massage);
    }
}
