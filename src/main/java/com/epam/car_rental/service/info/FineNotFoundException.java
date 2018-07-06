package com.epam.car_rental.service.info;

public class FineNotFoundException extends Exception {
    public FineNotFoundException(String massage){
        super(massage);
    }
}
