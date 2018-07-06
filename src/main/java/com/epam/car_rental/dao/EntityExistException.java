package com.epam.car_rental.dao;

public class EntityExistException extends DAOException {
    public EntityExistException(String massage){
        super(massage);
    }
}
