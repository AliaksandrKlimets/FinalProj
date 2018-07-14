package com.epam.car_rental.service.info;

import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> getOrders() throws ServiceException;

    Order getOrder(int orderId) throws ServiceException;

    void deleteOrder(int orderId) throws ServiceException;

    void addOrder(Order order, String expiryDate, String start, String end) throws ServiceException;

    void changeOrderState(int orderId, String state) throws ServiceException;

    void changePaymentState(int orderId, String state) throws ServiceException;

    void addDeclineReason(int orderId, String reason) throws ServiceException;

    List<Order> getUserOrders(int userId) throws ServiceException;
}
