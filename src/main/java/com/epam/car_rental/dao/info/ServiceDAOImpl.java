package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.Service;
import com.epam.car_rental.util.DAOUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class ServiceDAOImpl implements ServiceDAO {
    private static final Logger LOGGER = Logger.getLogger(ServiceDAOImpl.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("query.info.service");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Service> getServices() throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String getServices = bundle.getString(SERVICE_GET_SERVICES);
            PreparedStatement statement = connection.prepareStatement(getServices);
            ResultSet resultSet = statement.executeQuery();
            return DAOUtil.createServiceListFromDB(resultSet);
        }catch (SQLException e){
            LOGGER.error("Error while getting services list",e);
            throw new DAOException("Error while getting services list");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Car> getCarsByService(Service.Services service) throws DAOException {
        Connection connection= null;
        try{
            connection = connectionPool.getConnection();
            String getCars = bundle.getString(SERVICE_GET_CARS_BY_SERVICE);
            PreparedStatement statement = connection.prepareStatement(getCars);
            statement.setString(1,service.toString());
            ResultSet resultSet = statement.executeQuery();
            return DAOUtil.getCarListFromDB(resultSet);
        }catch (SQLException e){
            LOGGER.error("Error while getting car list by service",e);
            throw new DAOException("Error while getting car list by service");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addService(Service.Services service,int carId, double costPerHour, double oneToSeven, double eightToFifteen, double sixteenAndMore) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String add = bundle.getString(SERVICE_ADD_SERVICE);
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1,service.toString());
            statement.setInt(2,carId);
            statement.setDouble(3,costPerHour);
            statement.setDouble(4,oneToSeven);
            statement.setDouble(5,eightToFifteen);
            statement.setDouble(6,sixteenAndMore);
            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Error while adding service to db",e);
            throw new DAOException("Error while adding service to db");
        }
    }

    @Override
    public void deleteService(int serviceId) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String delete = bundle.getString(SERVICE_DELETE_SERVICES);
            DAOUtil.deleteEntity(serviceId,delete,connection);
        }catch (SQLException e){
            LOGGER.error("Error while deleting service by id",e);
            throw new DAOException("Error while deleting service by id");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }
}
