package com.epam.car_rental.dao.car;

import com.epam.car_rental.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.service.car.CarNotFoundException;

import java.util.List;
import java.util.ResourceBundle;

public class OrderedCarDAOImpl implements OrderedCarDAO {
    ResourceBundle bundle = ResourceBundle.getBundle("query.car");
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<OrderedCar> getOrderedCars() throws DAOException {
        return null;
    }

    @Override
    public List<OrderedCar> getCarOrders(int id) throws CarNotFoundException, DAOException {
        return null;
    }
}
