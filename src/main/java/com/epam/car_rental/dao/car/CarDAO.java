package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.entity.Car;

import java.util.List;

public interface CarDAO {
    List<Car> getCars(int begin, int size) throws DAOException;

    Car getCar(int id) throws DAOException;

    void deleteCar(int id) throws DAOException;

    void addCar(Car car) throws  DAOException;

    List<Car> getCarsByType(Car.Type type, int begin, int size) throws DAOException;

    int getCarIdByModel(String model) throws DAOException;

    int itemsCount() throws DAOException;

    int itemsByTypeCount(Car.Type type) throws DAOException;
}
