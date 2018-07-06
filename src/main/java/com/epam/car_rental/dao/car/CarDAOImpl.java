package com.epam.car_rental.dao.car;

import com.epam.car_rental.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.service.car.CarExistException;

import java.util.List;
import java.util.ResourceBundle;

public class CarDAOImpl implements CarDAO {
    ResourceBundle bundle = ResourceBundle.getBundle("query.car");
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Car> getCars() throws DAOException {
        return null;
    }

    @Override
    public Car getCar(int id) throws DAOException {
        return null;
    }

    @Override
    public void deleteCar(int id) throws DAOException {

    }

    @Override
    public void addCar(String model, int year, String consumption, double capacity,
                       Car.Type type, String transmission, Car.FuelType fuelType,
                       String image, String addInfo) throws CarExistException, DAOException {

    }

    @Override
    public List<Car> getCarByType(Car.Type type) throws DAOException {
        return null;
    }
}
