package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.OrderedCar;

import java.sql.Date;
import java.util.List;

public interface OrderedCarDAO {
    List<OrderedCar> getOrderedCars() throws DAOException;

    List<OrderedCar> getCarOrders(int id) throws  DAOException;

    void addCarToOrderedCarList(int carId, int userId, Date beginDate, Date endDate) throws DAOException;

    void deleteCarFromOrderedCarList(int carId) throws DAOException;

    List<OrderedCar> getActualCarOrders(int carId) throws DAOException;
}
