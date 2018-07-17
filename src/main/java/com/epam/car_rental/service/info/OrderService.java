package com.epam.car_rental.service.info;

import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> getOrders() throws ServiceException;

    Order getOrder(String orderId) throws ServiceException;

    void deleteOrder(String orderId) throws ServiceException;

    void addOrder(Order order, String userId, String carId, String expiryDate, String start, String end) throws ServiceException;

    void changeOrderState(String orderId, String state) throws ServiceException;

    void changePaymentState(String orderId, String state) throws ServiceException;

    void addDeclineReason(String orderId, String reason) throws ServiceException;

    List<Order> getUserOrders(String userId) throws ServiceException;
}
