package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.service.car.CarExistException;

import java.util.List;

public interface CarDAO {
    List<Car> getCars() throws DAOException;

    Car getCar(int id) throws DAOException;

    void deleteCar(int id) throws DAOException;

    void addCar(String model, int year, String consumption, double capacity,
                Car.Type type, String transmission, Car.FuelType fuelType,
                String image, String addInfo) throws CarExistException, DAOException;

    List<Car> getCarByType(Car.Type type) throws DAOException;
}
