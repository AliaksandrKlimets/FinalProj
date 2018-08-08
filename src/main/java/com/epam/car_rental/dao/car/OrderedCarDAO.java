package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.OrderedCar;

import java.sql.Date;
import java.util.List;

public interface OrderedCarDAO {
    List<OrderedCar> getOrderedCars(int begin, int size) throws DAOException;

    List<OrderedCar> getCarOrders(int id, int begin, int size) throws DAOException;

    void addCarToOrderedCarList(OrderedCar orderedCar) throws DAOException;

    void deleteCarFromOrderedCarList(int carId, String begin, String end) throws DAOException;

    List<OrderedCar> getActualCarOrders(int carId, int begin, int size) throws DAOException;

    void isDateAvailable(int carId, Date begin, Date end) throws DAOException;

    int itemsCount() throws DAOException;

    int carOrdersCount(int id) throws DAOException;

    int actualCarOrdersCount(int id) throws DAOException;
}
