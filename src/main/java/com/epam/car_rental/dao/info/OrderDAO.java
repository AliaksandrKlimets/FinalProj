package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.info.OrderNotFoundException;

import java.util.Date;
import java.util.List;

public interface OrderDAO {

    List<Order> getOrders() throws DAOException;

    Order getOrder(int orderId) throws DAOException, OrderNotFoundException;

    void deleteOrder(int orderId) throws DAOException, OrderNotFoundException;

    void addOrder(int UserId, int serviceId, String passportNumber, String idNumber,
                  Date dateOfExpiry, Date serviceStart, Date serviceEnd, double serviceCost) throws DAOException;

    void changeOrderState(int orderId) throws DAOException, OrderNotFoundException;

    void changePaymentState(int orderId) throws DAOException, OrderNotFoundException;

    void addDeclineReason(int orderId) throws DAOException, OrderNotFoundException;
}
