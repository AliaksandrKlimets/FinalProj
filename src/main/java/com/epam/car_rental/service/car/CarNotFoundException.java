package com.epam.car_rental.service.car;

public class CarNotFoundException extends Exception {
    public CarNotFoundException(String massage){
        super(massage);
    }
}
