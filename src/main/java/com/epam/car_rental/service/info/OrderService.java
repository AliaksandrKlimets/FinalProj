package com.epam.car_rental.service.info;

import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(int begin, int size) throws ServiceException;

    Order getOrder(String orderId) throws ServiceException;

    void deleteOrder(String orderId, String userId) throws ServiceException;

    void addOrder(Order order, String userId, String carId, String expiryDate, String start, String end) throws ServiceException;

    void changeOrderState(String orderId, String state) throws ServiceException;

    void changePaymentState(String orderId, String state) throws ServiceException;

    void addDeclineReason(String orderId, String reason) throws ServiceException;

    List<Order> getUserOrders(String userId, int begin, int size) throws ServiceException;

    int ordersCount() throws ServiceException;

    int userOrdersCount(int id) throws ServiceException;

    List<Order> getNewOrders(int begin, int size) throws ServiceException;

    int newOrderCount() throws ServiceException;
}
