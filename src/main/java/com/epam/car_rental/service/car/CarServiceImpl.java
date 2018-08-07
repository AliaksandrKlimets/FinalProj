package com.epam.car_rental.service.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.DAOFactory;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.dao.car.CarDAO;
import com.epam.car_rental.dao.car.DateNotAvailableException;
import com.epam.car_rental.dao.car.OrderedCarDAO;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.service.DateNotAvailableServiceException;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.service.validation.NotNumberException;
import com.epam.car_rental.service.validation.validator.CarValidator;
import com.epam.car_rental.service.validation.validator.Validator;

import java.sql.Date;
import java.util.List;

public class CarServiceImpl implements CarService {
    @Override
    public List<Car> getCars(int begin, int size) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            return carDAO.getCars(begin,size);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Car getCar(String id) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            Validator.isNumber(id);
            return carDAO.getCar(Integer.parseInt(id));
        }catch (EntityNotFoundException e){
            throw new CarNotFoundException(e.getMessage());
        }catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void deleteCar(String id) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            Validator.isNumber(id);
            carDAO.deleteCar(Integer.parseInt(id));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void addCar(Car car, String capacity, String carType, String fuelType, String day, String twoToSeven, String eightToFifteen, String more) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            CarValidator.isInputDataValid(car, capacity, carType, fuelType, day, twoToSeven, eightToFifteen, more);
            car.setEngineCapacity(Double.parseDouble(capacity));
            car.setType(carType.toUpperCase());
            car.setFuelType(fuelType.toUpperCase());
            car.setCostPerDay(Double.parseDouble(day));
            car.setTwoToSevenDays(Double.parseDouble(twoToSeven));
            car.setEightToFifteen(Double.parseDouble(eightToFifteen));
            car.setSixteenAndMore(Double.parseDouble(more));

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
    public List<Car> getCarsByType(String type, int begin, int size) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            CarValidator.isCarType(type);
            return carDAO.getCarsByType(Car.Type.valueOf(type.toUpperCase()), begin, size);
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getCarIdByModel(String model) throws ServiceException {
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
    public List<OrderedCar> getOrderedCars(int begin, int size) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            return orderedCarDAO.getOrderedCars(begin,size);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<OrderedCar> getCarOrders(String id, int begin, int size) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            Validator.isNumber(id);
            return orderedCarDAO.getCarOrders(Integer.parseInt(id), begin, size);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void addCarToOrderedCarList(OrderedCar orderedCar) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            orderedCarDAO.addCarToOrderedCarList(orderedCar);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteCarFromOrderedCarList(String carId, String begin, String end) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            Validator.isNumber(carId);
            orderedCarDAO.deleteCarFromOrderedCarList(Integer.parseInt(carId),begin,end);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public List<OrderedCar> getActualCarOrders(String carId, int begin, int size) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            Validator.isNumber(carId);
            return orderedCarDAO.getActualCarOrders(Integer.parseInt(carId), begin, size);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void isDateAvailable(String begin, String end) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            Validator.isDate(begin);
            Validator.isDate(end);
            orderedCarDAO.isDateAvailable(Date.valueOf(begin), Date.valueOf(end));
        } catch (DateNotAvailableException e) {
            throw new DateNotAvailableServiceException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public int itemsCount() throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            return carDAO.itemsCount();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int itemsByTypeCount(String type) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        try {
            return carDAO.itemsByTypeCount(Car.Type.valueOf(type));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int orderedItemsCount() throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            return orderedCarDAO.itemsCount();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int carOrdersCount(int id) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            return orderedCarDAO.carOrdersCount(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int actualCarOrdersCount(int id) throws ServiceException {
        OrderedCarDAO orderedCarDAO = DAOFactory.getInstance().getOrderedCarDAO();
        try {
            return orderedCarDAO.actualCarOrdersCount(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
