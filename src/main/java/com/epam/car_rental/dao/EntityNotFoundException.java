package com.epam.car_rental.dao;

public class EntityNotFoundException extends DAOException {
    public EntityNotFoundException(String massage){
        super(massage);
    }
}
