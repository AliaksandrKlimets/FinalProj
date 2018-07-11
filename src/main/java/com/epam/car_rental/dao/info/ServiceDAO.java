package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.Service;

import java.util.List;

public interface ServiceDAO {

    List<Service> getServices() throws DAOException;

    List<Car> getCarsByService(Service.Services service) throws DAOException;

    void addService(Service service) throws DAOException;

    void deleteService(int serviceId) throws DAOException;

}
