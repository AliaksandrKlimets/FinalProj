package com.epam.car_rental.service.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.DAOFactory;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.dao.car.CarDAO;
import com.epam.car_rental.dao.car.OrderedCarDAO;
import com.epam.car_rental.dao.info.OrderDAO;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.service.validation.validator.CarValidator;
import com.epam.car_rental.service.validation.validator.Validator;

import java.sql.Date;
import java.util.List;

public class CarServiceImpl implements CarService {
    @Override
    public List<Car> getCars() throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            return carDAO.getCars();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Car getCar(int id) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            return carDAO.getCar(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteCar(int id) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            carDAO.deleteCar(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void addCar(Car car, String capacity, String carType, String fuelType) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            CarValidator.isInputDataValid(car, capacity, carType, fuelType);
            car.setEngineCapacity(Double.parseDouble(capacity));
            car.setType(carType);
            car.setFuelType(fuelType);

            carDAO.addCar(car);
        } catch (EntityExistException e) {
            throw new CarExistException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public List<Car> getCarByType(String type) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            CarValidator.isCarType(type);
            return carDAO.getCarByType(Car.Type.valueOf(type));
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public long getCarIdByModel(String model) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            CarValidator.isModel(model);
            return carDAO.getCarIdByModel(model);
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<OrderedCar> getOrderedCars() throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            return orderedCarDAO.getOrderedCars();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<OrderedCar> getCarOrders(int id) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            return orderedCarDAO.getCarOrders(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void addCarToOrderedCarList(OrderedCar orderedCar) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try{
            orderedCarDAO.addCarToOrderedCarList(orderedCar);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteCarFromOrderedCarList(int carId) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try{
            orderedCarDAO.deleteCarFromOrderedCarList(carId);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<OrderedCar> getActualCarOrders(int carId) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try{
            return orderedCarDAO.getActualCarOrders(carId);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void isDateAvailable(String begin, String end) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try{
            Validator.isDate(begin);
            Validator.isDate(end);
            orderedCarDAO.isDateAvailable(Date.valueOf(begin),Date.valueOf(end));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }
}
