package com.epam.car_rental.dao;

import com.epam.car_rental.DAOException;

public class EntityNotFoundException extends DAOException {
    public EntityNotFoundException(String massage){
        super(massage);
    }
}
