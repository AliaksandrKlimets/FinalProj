package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.entity.Car;

import java.util.List;

public interface CarDAO {
    List<Car> getCars() throws DAOException;

    Car getCar(int id) throws DAOException;

    void deleteCar(int id) throws DAOException;

    void addCar(String model, String year, String consumption, double capacity,
                Car.Type type, String transmission, Car.FuelType fuelType,
                String image, String addInfo) throws EntityExistException, DAOException;

    List<Car> getCarByType(Car.Type type) throws DAOException;
}
