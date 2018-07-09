package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.info.OrderNotFoundException;
import com.epam.car_rental.util.DAOUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("query.info.order");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Order> getOrders() throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getOrders = bundle.getString(ORDER_GET_ORDERS);
            PreparedStatement statement = connection.prepareStatement(getOrders);
            ResultSet resultSet = statement.executeQuery();
            return DAOUtil.createOrderListFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting order list",e);
            throw new DAOException("Error while getting order list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Order getOrder(int orderId) throws DAOException, OrderNotFoundException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getOrder = bundle.getString(ORDER_GET_ORDER);
            PreparedStatement statement = connection.prepareStatement(getOrder);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                LOGGER.error("Cannot find order");
                throw new OrderNotFoundException("Cannot find order");
            }

            return DAOUtil.createOrderFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting order by order id",e);
            throw new DAOException("Error while getting order by order id");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void deleteOrder(int orderId) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String delete = bundle.getString(ORDER_DELETE_ORDER);
            DAOUtil.deleteEntity(orderId, delete, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while deleting order",e);
            throw new DAOException("Error while deleting order");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addOrder(int userId, int serviceId, String passportNumber, String idNumber,
                         Date dateOfExpiry, Date serviceStart, Date serviceEnd, double serviceCost) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String add = bundle.getString(ORDER_ADD_ORDER);
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setInt(1, userId);
            statement.setInt(2, serviceId);
            statement.setString(3, passportNumber);
            statement.setString(4, idNumber);
            statement.setDate(5, dateOfExpiry);
            statement.setDate(6, serviceStart);
            statement.setDate(7, serviceEnd);
            statement.setDouble(8, serviceCost);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error while adding order to db",e);
            throw new DAOException("Error while adding order to db");
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    @Override
    public void changeOrderState(int orderId, Order.OrderState state) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String change = bundle.getString(ORDER_CHANGE_ORDER_STATE);
            DAOUtil.changeInDB(orderId, state.toString(), change, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while changing order state",e);
            throw new DAOException("Error while changing order state");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changePaymentState(int orderId, Order.PaymentState state) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String change = bundle.getString(ORDER_CHANGE_PAYMENT_STATE);
            DAOUtil.changeInDB(orderId, state.toString(), change, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while changing payment state",e);
            throw new DAOException("Error while changing payment state");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addDeclineReason(int orderId, String reason) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String change = bundle.getString(ORDER_ADD_DECLINE_REASON);
            DAOUtil.changeInDB(orderId, reason, change, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while adding decline reason",e);
            throw new DAOException("Error while adding decline reason");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Order> getUserOrders(int userId) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getOrders = bundle.getString(ORDER_GET_USER_ORDERS);
            PreparedStatement statement = connection.prepareStatement(getOrders);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return DAOUtil.createOrderListFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting user order list",e);
            throw new DAOException("Error while getting user order list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

}