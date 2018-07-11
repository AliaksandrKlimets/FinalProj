package com.epam.car_rental.dao.car;

public class DateNotAvailableException extends Exception{
    public DateNotAvailableException(String massage){
        super(massage);
    }
}
