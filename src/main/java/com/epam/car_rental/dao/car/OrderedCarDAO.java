package com.epam.car_rental.dao.car;

import com.epam.car_rental.DAOException;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.service.car.CarNotFoundException;

import java.util.List;

public interface OrderedCarDAO {
    List<OrderedCar> getOrderedCars() throws DAOException;

    List<OrderedCar> getCarOrders(int id) throws CarNotFoundException, DAOException;
}
