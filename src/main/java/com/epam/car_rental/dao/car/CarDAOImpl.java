package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.util.DAOUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class CarDAOImpl implements CarDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDAOImpl.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("query.car");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Car> getCars() throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getCar = bundle.getString(CAR_GET_CARS);
            PreparedStatement statement = connection.prepareStatement(getCar);
            ResultSet resultSet = statement.executeQuery();

            return DAOUtil.getCarListFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting car list",e);
            throw new DAOException("Error while getting car list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Car getCar(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getCar = bundle.getString(CAR_GET_CAR);
            PreparedStatement statement = connection.prepareStatement(getCar);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                LOGGER.error("Cannot find car by id");
                throw new EntityNotFoundException("Cannot find car by id");
            }
            return DAOUtil.createCarFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Cannot create car from db",e);
            throw new DAOException("Cannot create car from db");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void deleteCar(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String deleteCar = bundle.getString(CAR_DELETE_CAR);
            DAOUtil.deleteEntity(id, deleteCar, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while deleting car from db",e);
            throw new DAOException("Error while deleting car from db");
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    @Override
    public void addCar(String model, String year, String consumption, double capacity,
                       Car.Type type, String transmission, Car.FuelType fuelType,
                       String image, String addInfo) throws EntityExistException, DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String check = bundle.getString(CAR_CHECK_CAR);
            PreparedStatement statement = connection.prepareStatement(check);
            statement.setString(1, model);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                LOGGER.error("Car exist");
                throw new EntityExistException("Car exist");
            }
            addCarToDB(model, year, consumption, capacity, type, transmission, fuelType, image, addInfo, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while adding car to db",e);
            throw new DAOException("Error while adding car to db");
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    private void addCarToDB(String model, String year, String consumption, double capacity,
                            Car.Type type, String transmission, Car.FuelType fuelType,
                            String image, String addInfo, Connection connection) throws SQLException {
        String add = bundle.getString(CAR_ADD_CAR);
        PreparedStatement statement = connection.prepareStatement(add);
        statement.setString(1, model);
        statement.setString(2, year);
        statement.setDouble(4, capacity);
        statement.setString(5, type.toString());
        statement.setString(6, transmission);
        statement.setString(7, fuelType.toString());
        statement.setString(8, image);
        statement.setString(9, addInfo);
        statement.executeUpdate();
    }

    @Override
    public List<Car> getCarByType(Car.Type type) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getCarByType = bundle.getString(CAR_GET_CARS_BY_TYPE);
            PreparedStatement statement = connection.prepareStatement(getCarByType);
            ResultSet resultSet = statement.executeQuery();
            statement.setString(1, type.toString());
            return DAOUtil.getCarListFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting car list by model",e);
            throw new DAOException("Error while getting car list by model");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

}
