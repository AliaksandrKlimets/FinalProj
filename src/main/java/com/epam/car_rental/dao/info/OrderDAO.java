package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.entity.Order;

import java.util.List;

public interface OrderDAO {

    /**
     * Return list of Order
     * @param begin Begin of selection
     * @param size Size of selection
     * @return {@link List} of {@link Order}
     * @throws DAOException if cannot find order
     */

    List<Order> getOrders(int begin, int size) throws DAOException;

    /**
     *
     * @param orderId Order id. Get order by this id
     * @return Order entity
     * @throws DAOException if database error occurred
     * @throws EntityNotFoundException if cannot find order
     */

    Order getOrder(int orderId) throws DAOException, EntityNotFoundException;

    /**
     *Delete order
     * @param orderId Order id. Delete order by this id
     * @param userId User id. Delete order by this id.
     * @throws DAOException if cannot find order
     */

    void deleteOrder(int orderId, int userId) throws DAOException;

    /**
     * Add new Order
     * @param order {@link Order} entity with some info
     * @throws DAOException if cannot find order
     */

    void addOrder(Order order) throws DAOException;

    /**
     * Change order state
     * @param orderId Order id. Change order state by this id.
     * @param state New order state
     * @throws DAOException if cannot find order
     */

    void changeOrderState(int orderId, Order.OrderState state) throws DAOException;

    /**
     * Change order payment state
     * @param orderId Order id. Change order state by this id.
     * @param state New order payment state
     * @throws DAOException if cannot find order
     */

    void changePaymentState(int orderId, Order.PaymentState state) throws DAOException;

    /**
     * Add decline reason
     * @param orderId Order id. Add reason by this id
     * @param reason Decline reason
     * @throws DAOException if cannot find order
     */

    void addDeclineReason(int orderId, String reason) throws DAOException;

    /**
     * Return list of user Order
     * @param begin Begin of selection
     * @param size Size of selection
     * @return {@link List} of user {@link Order}
     * @throws DAOException if cannot find order
     */

    List<Order> getUserOrders(int userId, int begin, int size) throws DAOException;

    /**
     * Return count of order
     * @return count of order
     * @throws DAOException if cannot find order
     */

    int ordersCount() throws DAOException;

    /**
     * Return count of user order
     * @return count of user order
     * @throws DAOException if cannot find order
     */

    int userOrdersCount(int id) throws DAOException;

    /**
     * Return list of new Order
     * @param begin Begin of selection
     * @param size Size of selection
     * @return {@link List} of new {@link Order}
     * @throws DAOException if cannot find order
     */

    List<Order> getNewOrders(int begin, int size) throws DAOException;

    /**
     * Return count of new order
     * @return count of new order
     * @throws DAOException if cannot find order
     */

    int newOrderCount() throws DAOException;
}
