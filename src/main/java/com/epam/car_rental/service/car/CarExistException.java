package com.epam.car_rental.service.car;

public class CarExistException extends Exception {
    public CarExistException(String massage){
        super(massage);
    }
}
