package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.Service;

import java.util.List;
import java.util.ResourceBundle;

public class ServiceDAOImpl implements ServiceDAO {
    ResourceBundle bundle = ResourceBundle.getBundle("query.info.service");
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Service> getServices() throws DAOException {
        return null;
    }

    @Override
    public List<Car> getCarsByService(Service.Services service) throws DAOException {
        return null;
    }

    @Override
    public void addService(Service.Services service, double costPerHour, double oneToSeven, double eightToFifteen, double sixteenAndMore) throws DAOException {

    }

    @Override
    public void deleteService(int serviceId) throws DAOException {

    }
}
