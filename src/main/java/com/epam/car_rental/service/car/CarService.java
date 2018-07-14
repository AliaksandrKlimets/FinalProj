package com.epam.car_rental.service.car;

import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.service.ServiceException;

import java.sql.Date;
import java.util.List;

public interface CarService {
    List<Car> getCars() throws ServiceException;

    Car getCar(int id) throws ServiceException;

    void deleteCar(int id) throws ServiceException;

    void addCar(Car car, String capacity, String carType, String fuelType) throws  ServiceException;

    List<Car> getCarByType(String type) throws ServiceException;

    long getCarIdByModel(String model) throws ServiceException;

    List<OrderedCar> getOrderedCars() throws ServiceException;

    List<OrderedCar> getCarOrders(int id) throws  ServiceException;

    void addCarToOrderedCarList(OrderedCar orderedCar) throws ServiceException;

    void deleteCarFromOrderedCarList(int carId) throws ServiceException;

    List<OrderedCar> getActualCarOrders(int carId) throws ServiceException;

    void isDateAvailable(String begin, String end)throws ServiceException;
}
