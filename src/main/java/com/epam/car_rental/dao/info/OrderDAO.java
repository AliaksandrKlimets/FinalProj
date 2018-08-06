package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.entity.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> getOrders(int begin, int size) throws DAOException;

    Order getOrder(int orderId) throws DAOException, EntityNotFoundException;

    void deleteOrder(int orderId, int userId) throws DAOException;

    void addOrder(Order order) throws DAOException;

    void changeOrderState(int orderId, Order.OrderState state) throws DAOException;

    void changePaymentState(int orderId, Order.PaymentState state) throws DAOException;

    void addDeclineReason(int orderId, String reason) throws DAOException;

    List<Order> getUserOrders(int userId, int begin, int size) throws DAOException;

    int ordersCount() throws DAOException;

    int userOrdersCount(int id) throws DAOException;

    List<Order> getNewOrders(int begin, int size) throws DAOException;

    int newOrderCount() throws DAOException;
}
