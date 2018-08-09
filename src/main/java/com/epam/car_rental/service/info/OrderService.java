package com.epam.car_rental.service.info;

import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface OrderService {
    /**
     * Returns {@link List} of {@link Order} using DAO layer
     * @param begin Begin of selection
     * @param size Selection size
     * @return {@link List} of {@link Order}
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<Order> getOrders(int begin, int size) throws ServiceException;

    /**
     * Returns {@link Order} entity
     * @param orderId order Id
     * @return {@link Order} entity
     * @throws ServiceException if DAO layer throws DAOException
     */

    Order getOrder(String orderId) throws ServiceException;

    /**
     * Delete order using DAO layer
     * @param orderId Order id
     * @param userId User id
     * @throws ServiceException if DAO layer throws DAOException
     */

    void deleteOrder(String orderId, String userId) throws ServiceException;

    /**
     * Add new Order using DAO layer
     * @param order order entity with info
     * @param userId User id
     * @param carId Car id
     * @param expiryDate date of Expiry
     * @param start Service start
     * @param end Service end
     * @throws ServiceException if DAO layer throws DAOException
     */

    void addOrder(Order order, String userId, String carId, String expiryDate, String start, String end) throws ServiceException;

    /**
     * Change order state using DAO layer
     * @param orderId order Id
     * @param state new state
     * @throws ServiceException if DAO layer throws DAOException
     */

    void changeOrderState(String orderId, String state) throws ServiceException;

    /**
     * Change order payment state using DAO layer
     * @param orderId order Id
     * @param state new state
     * @throws ServiceException if DAO layer throws DAOException
     */

    void changePaymentState(String orderId, String state) throws ServiceException;

    /**
     * Add decline reason using DAO layer
     * @param orderId order id
     * @param reason decline reason
     * @throws ServiceException if DAO layer throws DAOException
     */

    void addDeclineReason(String orderId, String reason) throws ServiceException;

    /**
     * Returns {@link List} of User {@link Order}
     * @param userId User id
     * @param begin Begin of selection
     * @param size Selection size
     * @return {@link List} of User {@link Order}
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<Order> getUserOrders(String userId, int begin, int size) throws ServiceException;

    /**
     * Returns order count using DAO layer
     * @return order count using DAO layer
     * @throws ServiceException if DAO layer throws DAOException
     */

    int ordersCount() throws ServiceException;

    /**
     * Returns user order count using DAO layer
     * @return user order count using DAO layer
     * @throws ServiceException if DAO layer throws DAOException
     */

    int userOrdersCount(int id) throws ServiceException;

    /**
     * Returns list of new Orders
     * @param begin Begin of selection
     * @param size Selection size
     * @return list of new Orders
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<Order> getNewOrders(int begin, int size) throws ServiceException;

    /**
     * Returns new order count using DAO layer
     * @return new order count using DAO layer
     * @throws ServiceException if DAO layer throws DAOException
     */

    int newOrderCount() throws ServiceException;
}
