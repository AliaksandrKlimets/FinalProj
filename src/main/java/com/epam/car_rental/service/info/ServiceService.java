package com.epam.car_rental.service.info;

import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.Service;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface ServiceService {

    List<Service> getServices() throws ServiceException;

    List<Car> getCarsByService(String service) throws ServiceException;

    void addService(String carId, String service, String hour, String oneToSeven, String sevenToFifteen, String more) throws ServiceException;

    void deleteService(String serviceId) throws ServiceException;

    int getServiceIdByModelAndService(String model, String service) throws ServiceException;

}
