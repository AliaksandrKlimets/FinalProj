package com.epam.car_rental.dao;

import com.epam.car_rental.dao.car.CarDAO;
import com.epam.car_rental.dao.car.CarDAOImpl;
import com.epam.car_rental.dao.car.OrderedCarDAO;
import com.epam.car_rental.dao.car.OrderedCarDAOImpl;
import com.epam.car_rental.dao.info.*;
import com.epam.car_rental.dao.user.UserDAO;
import com.epam.car_rental.dao.user.UserDAOImpl;
import com.epam.car_rental.dao.user.UserInfoDAO;
import com.epam.car_rental.dao.user.UserInfoDAOImpl;

public class DAOFactory {
    private static volatile DAOFactory instance;

    private final UserDAO userDAO = new UserDAOImpl();
    private final UserInfoDAO userInfoDAO = new UserInfoDAOImpl();
    private final CarDAO carDAO = new CarDAOImpl();
    private final OrderedCarDAO orderedCarDAO = new OrderedCarDAOImpl();
    private final FineDAO fineDAO = new FineDAOImpl();
    private final ServiceDAO serviceDAO = new ServiceDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public static void setInstance(DAOFactory instance) {
        DAOFactory.instance = instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public OrderedCarDAO getOrderedCarDAO() {
        return orderedCarDAO;
    }

    public FineDAO getFineDAO() {
        return fineDAO;
    }

    public ServiceDAO getServiceDAO() {
        return serviceDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}