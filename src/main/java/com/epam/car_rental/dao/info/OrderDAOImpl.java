package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.info.OrderNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDAOImpl implements OrderDAO {

    ResourceBundle bundle = ResourceBundle.getBundle("query.info.order");
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Order> getOrders() throws DAOException {
        return null;
    }

    @Override
    public Order getOrder(int orderId) throws DAOException, OrderNotFoundException {
        return null;
    }

    @Override
    public void deleteOrder(int orderId) throws DAOException, OrderNotFoundException {

    }

    @Override
    public void addOrder(int UserId, int serviceId, String passportNumber, String idNumber,
                         Date dateOfExpiry, Date serviceStart, Date serviceEnd, double serviceCost) throws DAOException {

    }

    @Override
    public void changeOrderState(int orderId) throws DAOException, OrderNotFoundException {

    }

    @Override
    public void changePaymentState(int orderId) throws DAOException, OrderNotFoundException {

    }

    @Override
    public void addDeclineReason(int orderId) throws DAOException, OrderNotFoundException {

    }
}
