package com.epam.car_rental.dao;

import com.epam.car_rental.dao.car.CarDAO;
import com.epam.car_rental.dao.car.CarDAOImpl;
import com.epam.car_rental.dao.car.OrderedCarDAO;
import com.epam.car_rental.dao.car.OrderedCarDAOImpl;
import com.epam.car_rental.dao.info.*;
import com.epam.car_rental.dao.user.UserDAO;
import com.epam.car_rental.dao.user.UserDAOImpl;

public class DAOFactory {
    private static volatile DAOFactory instance;

    private final UserDAO userDAO = new UserDAOImpl();
    private final CarDAO carDAO = new CarDAOImpl();
    private final OrderedCarDAO orderedCarDAO = new OrderedCarDAOImpl();
    private final FineDAO fineDAO = new FineDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();

    private DAOFactory() {
    }

    /**
     * Returns DAO Factory instance.
     *
     * @return instance of DAOFactory
     */

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    /**
     * Returns User DAO implementations.
     *
     * @return User DAO
     */

    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Returns Car DAO implementations.
     *
     * @return Car DAO
     */

    public CarDAO getCarDAO() {
        return carDAO;
    }

    /**
     * Returns Ordered Car DAO implementations.
     *
     * @return Ordered Car DAO
     */

    public OrderedCarDAO getOrderedCarDAO() {
        return orderedCarDAO;
    }

    /**
     * Returns Fine DAO implementations.
     *
     * @return Fine DAO
     */

    public FineDAO getFineDAO() {
        return fineDAO;
    }

    /**
     * Returns Order DAO implementations.
     *
     * @return Order DAO
     */

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}