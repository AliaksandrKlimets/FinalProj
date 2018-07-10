package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.entity.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDAO {

    List<Order> getOrders() throws DAOException;

    Order getOrder(int orderId) throws DAOException, EntityNotFoundException;

    void deleteOrder(int orderId) throws DAOException;

    void addOrder(int UserId, int serviceId, String passportNumber, String idNumber,
                  Date dateOfExpiry, Date serviceStart, Date serviceEnd, double serviceCost) throws DAOException;

    void changeOrderState(int orderId, Order.OrderState state) throws DAOException;

    void changePaymentState(int orderId, Order.PaymentState state) throws DAOException;

    void addDeclineReason(int orderId, String reason) throws DAOException;

    List<Order> getUserOrders(int userId) throws DAOException;
}
