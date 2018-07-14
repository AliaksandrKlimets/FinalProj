package com.epam.car_rental.service.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.DAOFactory;
import com.epam.car_rental.dao.info.ServiceDAO;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.Service;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.service.validation.validator.CarValidator;
import com.epam.car_rental.service.validation.validator.ServiceValidator;
import com.epam.car_rental.service.validation.validator.Validator;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {
    @Override
    public List<Service> getServices() throws ServiceException {
        ServiceDAO serviceDAO = DAOFactory.getInstance().getServiceDAO();
        try{
            return serviceDAO.getServices();
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Car> getCarsByService(String service) throws ServiceException {
        ServiceDAO serviceDAO = DAOFactory.getInstance().getServiceDAO();
        try{
            ServiceValidator.isService(service);
            return serviceDAO.getCarsByService(Service.Services.valueOf(service));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void addService(String carId,String service, String hour, String oneToSeven, String eightToFifteen, String more) throws ServiceException {
        ServiceDAO serviceDAO = DAOFactory.getInstance().getServiceDAO();
        try{
            Validator.isNumber(carId);
            ServiceValidator.isServiceDataValid(service,hour,oneToSeven,eightToFifteen,more);
            Service serviceObj = new Service();
            serviceObj.setService(service);
            serviceObj.setCarId(Integer.parseInt(carId));
            serviceObj.setCostPerHour(Double.parseDouble(hour));
            serviceObj.setOneToSevenDays(Double.parseDouble(oneToSeven));
            serviceObj.setEightToFifteen(Double.parseDouble(eightToFifteen));
            serviceObj.setSixteenAndMore(Double.parseDouble(more));
            serviceDAO.addService(serviceObj);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void deleteService(String serviceId) throws ServiceException {
        ServiceDAO serviceDAO = DAOFactory.getInstance().getServiceDAO();
        try{
            Validator.isNumber(serviceId);
            serviceDAO.deleteService(Integer.parseInt(serviceId));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public int getServiceIdByModelAndService(String model, String service) throws ServiceException {
        ServiceDAO serviceDAO = DAOFactory.getInstance().getServiceDAO();
        try{
            CarValidator.isModel(model);
            ServiceValidator.isService(service);
            return serviceDAO.getServiceIdByModelAndService(model,service);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }
}
