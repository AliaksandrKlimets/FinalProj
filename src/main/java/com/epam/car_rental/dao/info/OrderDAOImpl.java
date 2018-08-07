package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Order;
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
    public List<Order> getOrders(int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getOrders = bundle.getString(ORDER_GET_ORDERS);
            PreparedStatement statement = connection.prepareStatement(getOrders);
            statement.setInt(1,size);
            statement.setInt(2,begin);
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
    public Order getOrder(int orderId) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getOrder = bundle.getString(ORDER_GET_ORDER);
            PreparedStatement statement = connection.prepareStatement(getOrder);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                LOGGER.error("Cannot find order");
                throw new EntityNotFoundException("Cannot find order");
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
    public void deleteOrder(int orderId, int userId) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String delete = bundle.getString(ORDER_DELETE_ORDER);
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1,orderId);
            statement.setInt(2,userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error while deleting order",e);
            throw new DAOException("Error while deleting order");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addOrder(Order order) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String add = bundle.getString(ORDER_ADD_ORDER);
            String check= bundle.getString(CAR_GET_CAR);
            PreparedStatement statement = connection.prepareStatement(check);
            statement.setInt(1,order.getCarId());
            ResultSet set = statement.executeQuery();

            if(!set.isBeforeFirst()){
                LOGGER.error("Car not found");
                throw new EntityNotFoundException("Car not found");
            }

            statement = connection.prepareStatement(add);
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getCarId());
            statement.setString(3, order.getPassportNumber());
            statement.setString(4, order.getIdentificationNumber());
            statement.setDate(5, order.getDateOfExpiry());
            statement.setDate(6, order.getServiceStart());
            statement.setDate(7, order.getServiceEnd());
            statement.setDouble(8, order.getServiceCost());
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
    public List<Order> getUserOrders(int userId, int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getOrders = bundle.getString(ORDER_GET_USER_ORDERS);
            PreparedStatement statement = connection.prepareStatement(getOrders);
            statement.setInt(1, userId);
            statement.setInt(2, size);
            statement.setInt(3, begin);
            ResultSet resultSet = statement.executeQuery();
            return DAOUtil.createOrderListFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting user order list",e);
            throw new DAOException("Error while getting user order list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int ordersCount() throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String orderCount = bundle.getString(ORDER_ITEMS_COUNT);
            return DAOUtil.getCount(orderCount,connection);
        }catch (SQLException e){
            LOGGER.error("Cannot count order items");
            throw new DAOException("Cannot count order items");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int userOrdersCount(int id) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String orderCount = bundle.getString(ORDER_USER_ITEMS_COUNT);
            return DAOUtil.getParamCount(id,orderCount,connection);
        }catch (SQLException e){
            LOGGER.error("Cannot count user order items");
            throw new DAOException("Cannot user count order items");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Order> getNewOrders(int begin, int size) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String newOrders = bundle.getString(ORDER_GET_NEW_ORDER);
            PreparedStatement statement = connection.prepareStatement(newOrders);
            statement.setInt(1,size);
            statement.setInt(2,begin);
            ResultSet set = statement.executeQuery();
            return DAOUtil.createOrderListFromDB(set);
        }catch (SQLException e){
            LOGGER.error("Error while getting new orders list");
            throw new DAOException("Error while getting new orders list");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int newOrderCount() throws DAOException{
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String orderCount = bundle.getString(ORDER_NEW_ITEMS_COUNT);
            return DAOUtil.getCount(orderCount,connection);
        }catch (SQLException e){
            LOGGER.error("Cannot count new order items");
            throw new DAOException("Cannot count new order items");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }
}