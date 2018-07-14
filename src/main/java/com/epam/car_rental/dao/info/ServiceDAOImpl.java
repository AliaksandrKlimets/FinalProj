package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
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
    public void addService(Service service) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String add = bundle.getString(SERVICE_ADD_SERVICE);
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1,service.getService().toString());
            statement.setInt(2,service.getCarId());
            statement.setDouble(3,service.getCostPerHour());
            statement.setDouble(4,service.getOneToSevenDays());
            statement.setDouble(5,service.getEightToFifteen());
            statement.setDouble(6,service.getSixteenAndMore());
            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Error while adding service to db",e);
            throw new DAOException("Error while adding service to db");
        }finally {
            connectionPool.closeConnection(connection);
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

    @Override
    public int getServiceIdByModelAndService(String model, String service) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String getId = bundle.getString(SERVICE_GET_ID);
            PreparedStatement statement = connection.prepareStatement(getId);
            statement.setString(1,model);
            statement.setString(2,service);
            ResultSet set = statement.executeQuery();

            if(!set.isBeforeFirst()){
                throw new EntityNotFoundException("Cannot find service id by model");
            }

            return set.getInt(1);
        }catch (SQLException e){
            LOGGER.error("Cannot get service is by model and service");
            throw new DAOException("Cannot get service is by model and service");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }
}
