package com.epam.car_rental.service.info;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String massage){
        super(massage);
    }
}
