package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.util.DAOUtil;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class OrderedCarDAOImpl implements OrderedCarDAO {
    private ResourceBundle bundle = ResourceBundle.getBundle("query.car");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void addCarToOrderedCarList(int carId, int userId, Date beginDate, Date endDate) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String addCarToList = bundle.getString(ORDERED_CAR_ADD_CAR_ORDER);
            PreparedStatement statement = connection.prepareStatement(addCarToList);
            statement.setInt(1,carId);
            statement.setInt(2,userId);
            statement.setDate(3,beginDate);
            statement.setDate(4,endDate);
            statement.executeUpdate();
        }catch (SQLException e ){
            throw new DAOException("Cannot add car to ordered car list");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void deleteCarFromOrderedCarList(int carId) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String deleteCar = bundle.getString(ORDERED_CAR_DELETE_CAR_ORDER);
            DAOUtil.deleteEntity(carId, deleteCar, connection);
        } catch (SQLException e) {
            throw new DAOException("Error while deleting ordered car list from db");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<OrderedCar> getActualCarOrders(int carId) throws DAOException {
        Connection connection = null;
        try{
            connection  = connectionPool.getConnection();
            String carOrders = bundle.getString(ORDERED_CAR_GET_ACTUAL_CAR_ORDERS);
            return getOrderedList(carId,carOrders,connection);
        }catch (SQLException e){
            throw new DAOException("Error while getting actual ordered car list by id");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<OrderedCar> getOrderedCars() throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String getCars = bundle.getString(ORDERED_CAR_GET_CARS);
            PreparedStatement statement = connection.prepareStatement(getCars);
            ResultSet resultSet = statement.executeQuery();
            return DAOUtil.createOrderedCarListFromDB(resultSet);
        }catch (SQLException e){
            throw new DAOException("Error while getting ordered car list");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<OrderedCar> getCarOrders(int id) throws DAOException {
        Connection connection = null;
        try{
            connection  = connectionPool.getConnection();
            String carOrders = bundle.getString(ORDERED_CAR_GET_CAR_ORDERS);
            return getOrderedList(id,carOrders,connection);
        }catch (SQLException e){
            throw new DAOException("Error while getting ordered car by id");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    private List<OrderedCar> getOrderedList(int carId, String query, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,carId);
        ResultSet resultSet = statement.executeQuery();
        return DAOUtil.createOrderedCarListFromDB(resultSet);
    }
}
