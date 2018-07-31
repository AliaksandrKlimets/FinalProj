package com.epam.car_rental.service.car;

import com.epam.car_rental.dao.car.DateNotAvailableException;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface CarService {
    List<Car> getCars(int begin, int size) throws ServiceException;

    int itemsCount() throws ServiceException;

    int itemsByTypeCount(String type) throws ServiceException;

    Car getCar(String id) throws ServiceException;

    void deleteCar(String id) throws ServiceException;

    void addCar(Car car, String capacity, String carType, String fuelType, String hour, String oneToSeven, String eightToFifteen, String more) throws ServiceException;

    List<Car> getCarsByType(String type, int begin, int size) throws ServiceException;

    int getCarIdByModel(String model) throws ServiceException;

    List<OrderedCar> getOrderedCars(int begin, int size) throws ServiceException;

    List<OrderedCar> getCarOrders(String id, int begin, int size) throws ServiceException;

    void addCarToOrderedCarList(OrderedCar orderedCar) throws ServiceException;

    void deleteCarFromOrderedCarList(String carId) throws ServiceException;

    List<OrderedCar> getActualCarOrders(String carId, int begin, int size) throws ServiceException;

    void isDateAvailable(String begin, String end) throws ServiceException, DateNotAvailableException;

    int orderedItemsCount() throws ServiceException;

    int carOrdersCount(int id) throws ServiceException;

    int actualCarOrdersCount(int id) throws ServiceException;
}
