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
    public List<Car> getCars(int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getCar = bundle.getString(CAR_GET_CARS);
            PreparedStatement statement = connection.prepareStatement(getCar);
            statement.setInt(1,size);
            statement.setInt(2,begin);
            ResultSet resultSet = statement.executeQuery();

            return DAOUtil.getCarListFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting car list", e);
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
            LOGGER.error("Cannot create car from db", e);
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
            LOGGER.error("Error while deleting car from db", e);
            throw new DAOException("Error while deleting car from db");
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    @Override
    public int getCarIdByModel(String model) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getCar = bundle.getString(CAR_GET_CAR_BY_MODEL);
            PreparedStatement statement = connection.prepareStatement(getCar);
            statement.setString(1, model);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                LOGGER.error("Cannot find car by model");
                throw new EntityNotFoundException("Cannot find car by model");
            }
            return resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.error("Cannot create car from db", e);
            throw new DAOException("Cannot create car from db");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addCar(Car car) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String check = bundle.getString(CAR_CHECK_CAR);
            PreparedStatement statement = connection.prepareStatement(check);
            statement.setString(1, car.getModel());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                LOGGER.error("Car exist");
                throw new EntityExistException("Car exist");
            }
            addCarToDB(car, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while adding car to db", e);
            throw new DAOException("Error while adding car to db");
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    private void addCarToDB(Car car, Connection connection) throws SQLException {
        String add = bundle.getString(CAR_ADD_CAR);
        String id = bundle.getString(CAR_GET_CAR_BY_MODEL);
        String addServiceCost = bundle.getString(CAR_ADD_SERVICE_COST);

        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(add);
        statement.setString(1, car.getModel());
        statement.setString(2, car.getYear());
        statement.setString(3, car.getConsumption());
        statement.setDouble(4, car.getEngineCapacity());
        statement.setString(5, car.getType().toString());
        statement.setString(6, car.getTransmission());
        statement.setString(7, car.getFuelType().toString());
        statement.setString(8, car.getImage());
        statement.setString(9, car.getAddInfo());
        statement.executeUpdate();

        statement = connection.prepareStatement(id);
        statement.setString(1, car.getModel());
        ResultSet set = statement.executeQuery();

        int carId=0;

        while(set.next()){
            carId = set.getInt(1);
        }

        statement = connection.prepareStatement(addServiceCost);
        statement.setInt(1, carId);
        statement.setDouble(2, car.getCostPerDay());
        statement.setDouble(3, car.getTwoToSevenDays());
        statement.setDouble(4, car.getEightToFifteen());
        statement.setDouble(5, car.getSixteenAndMore());
        statement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public List<Car> getCarsByType(Car.Type type, int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getCarByType = bundle.getString(CAR_GET_CARS_BY_TYPE);
            PreparedStatement statement = connection.prepareStatement(getCarByType);
            statement.setString(1, type.toString());
            statement.setInt(2,size);
            statement.setInt(3,begin);
            ResultSet resultSet = statement.executeQuery();

            return DAOUtil.getCarListFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting car list by model", e);
            throw new DAOException("Error while getting car list by model");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }


    @Override
    public int itemsCount() throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String count = bundle.getString(CAR_ITEMS_COUNT);
            return DAOUtil.getCount(count, connection);
        }catch (SQLException e){
            LOGGER.error("Cannot count items");
            throw new DAOException("Cannot count items");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int itemsByTypeCount(Car.Type type) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String count = bundle.getString(CAR_ITEMS_COUNT_BY_TYPE);
            PreparedStatement statement = connection.prepareStatement(count);
            statement.setString(1,type.toString());
            ResultSet set = statement.executeQuery();
            int result = 0;
            while (set.next()){
                result = set.getInt(1);
            }
            return result;
        }catch (SQLException e){
            LOGGER.error("Cannot count items");
            throw new DAOException("Cannot count items");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }
}
