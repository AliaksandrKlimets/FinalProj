package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.dao.info.OrderDAOImpl;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.util.DAOUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class OrderedCarDAOImpl implements OrderedCarDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("query.car");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void isDateAvailable(Date begin, Date end) throws DAOException{
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String isAvailable = bundle.getString(ORDERED_CAR_DATE_AVAILABLE);
            PreparedStatement statement = connection.prepareStatement(isAvailable);
            statement.setDate(1,begin);
            statement.setDate(2,end);
            statement.setDate(3,begin);
            statement.setDate(4,end);
            ResultSet set = statement.executeQuery();
            if(set.isBeforeFirst()){
                LOGGER.error("No available date");
                throw new DateNotAvailableException("No available date");
            }
        }catch (SQLException e){
            LOGGER.error("Cannot check date available");
            throw new DAOException("Cannot check date available");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addCarToOrderedCarList(OrderedCar orderedCar) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String addCarToList = bundle.getString(ORDERED_CAR_ADD_CAR_ORDER);
            PreparedStatement statement = connection.prepareStatement(addCarToList);
            statement.setInt(1, orderedCar.getCarId());
            statement.setInt(2, orderedCar.getUserId());
            statement.setDate(3, orderedCar.getBeginDate());
            statement.setDate(4, orderedCar.getEndDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot add car to ordered car list",e);
            throw new DAOException("Cannot add car to ordered car list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void deleteCarFromOrderedCarList(int carId, String begin, String end) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String deleteCar = bundle.getString(ORDERED_CAR_DELETE_CAR_ORDER);
            PreparedStatement statement = connection.prepareStatement(deleteCar);
            statement.setInt(1,carId);
            statement.setDate(2,Date.valueOf(begin));
            statement.setDate(3,Date.valueOf(end));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error while deleting ordered car list from db",e);
            throw new DAOException("Error while deleting ordered car list from db");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<OrderedCar> getActualCarOrders(int carId, int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String carOrders = bundle.getString(ORDERED_CAR_GET_ACTUAL_CAR_ORDERS);
            return getOrderedList(carId, begin, size, carOrders, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while getting actual ordered car list by id",e);
            throw new DAOException("Error while getting actual ordered car list by id");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<OrderedCar> getOrderedCars(int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getCars = bundle.getString(ORDERED_CAR_GET_CARS);
            PreparedStatement statement = connection.prepareStatement(getCars);
            statement.setInt(1,size);
            statement.setInt(2,begin);
            ResultSet resultSet = statement.executeQuery();
            return DAOUtil.createOrderedCarListFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting ordered car list",e);
            throw new DAOException("Error while getting ordered car list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<OrderedCar> getCarOrders(int id, int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String carOrders = bundle.getString(ORDERED_CAR_GET_CAR_ORDERS);
            return getOrderedList(id, begin, size, carOrders, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while getting ordered car by id",e);
            throw new DAOException("Error while getting ordered car by id");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    private List<OrderedCar> getOrderedList(int carId,int begin, int size, String query, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, carId);
        statement.setInt(2, size);
        statement.setInt(3, begin);
        ResultSet resultSet = statement.executeQuery();
        return DAOUtil.createOrderedCarListFromDB(resultSet);
    }

    @Override
    public int actualCarOrdersCount(int id) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String count = bundle.getString(ORDERED_CAR_ACTUAL_COUNT);
            return DAOUtil.getParamCount(id,count,connection);
        }catch (SQLException e){
            LOGGER.error("Cannot count actual ordered car items", e);
            throw new DAOException("Cannot count actual ordered car items");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int itemsCount() throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String count = bundle.getString(ORDERED_CAR_COUNT);
            return DAOUtil.getCount(count,connection);
        }catch (SQLException e){
            LOGGER.error("Cannot count ordered car items",e);
            throw new DAOException("Cannot count ordered car items");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int carOrdersCount(int id) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String count = bundle.getString(ORDERED_CAR_CAR_COUNT);
            return DAOUtil.getParamCount(id,count,connection);
        }catch (SQLException e){
            LOGGER.error("Cannot count ordered car items", e);
            throw new DAOException("Cannot count ordered car items");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }
}
