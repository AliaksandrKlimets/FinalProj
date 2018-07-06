package com.epam.car_rental.dao.connector;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String massage, Exception e){
        super(massage,e);
    }

    public ConnectionPoolException(String massage){
        super(massage);
    }
}
