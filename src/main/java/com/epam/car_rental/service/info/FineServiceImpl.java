package com.epam.car_rental.service.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.DAOFactory;
import com.epam.car_rental.dao.info.FineDAO;
import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.service.validation.validator.FineValidator;
import com.epam.car_rental.service.validation.validator.Validator;

import java.sql.Date;
import java.util.List;

public class FineServiceImpl implements FineService {
    @Override
    public List<Fine> getFines(int begin, int size) throws ServiceException {
        FineDAO fineDAO = DAOFactory.getInstance().getFineDAO();
        try{
            return fineDAO.getFines(begin,size);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Fine> getUserFines(String userId, int begin, int size) throws ServiceException {
        FineDAO fineDAO = DAOFactory.getInstance().getFineDAO();
        try{
            Validator.isNumber(userId);
            return fineDAO.getUserFines(Integer.parseInt(userId),begin,size);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changePaymentState(String fineId, String state, String number) throws ServiceException {
        FineDAO fineDAO = DAOFactory.getInstance().getFineDAO();
        try{
            Validator.isNumber(fineId);
            Validator.isNumber(number);
            FineValidator.isPaymentState(state);
            fineDAO.changePaymentState(Integer.parseInt(fineId),Fine.State.valueOf(state));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void deleteFine(String fineId) throws ServiceException {
        FineDAO fineDAO = DAOFactory.getInstance().getFineDAO();
        try{
            Validator.isNumber(fineId);
            fineDAO.deleteFine(Integer.parseInt(fineId));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void addFine(String userId,String carId, String cause, String bill, String dueDate) throws ServiceException {
        FineDAO fineDAO = DAOFactory.getInstance().getFineDAO();
        try{
            Validator.isDouble(bill);
            Validator.isDate(dueDate);
            Validator.isNumber(userId);
            Validator.isNumber(carId);
            Fine fine = new Fine();
            fine.setUserId(Integer.parseInt(userId));
            fine.setCarId(Integer.parseInt(carId));
            fine.setCause(cause);
            fine.setRepairBill(Double.parseDouble(bill));
            fine.setDueDate(Date.valueOf(dueDate));
            fineDAO.addFine(fine);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public List<Fine> getUnpaidFines(int begin, int size) throws ServiceException {
        FineDAO fineDAO = DAOFactory.getInstance().getFineDAO();
        try{
            return fineDAO.getUnpaidFines(begin,size);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int finesCount() throws ServiceException {
        FineDAO fineDAO = DAOFactory.getInstance().getFineDAO();
        try{
            return fineDAO.finesCount();
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int userFinesCount(int id) throws ServiceException {
        FineDAO fineDAO = DAOFactory.getInstance().getFineDAO();
        try{
            return fineDAO.userFinesCount(id);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int unpaidFinesCount() throws ServiceException {
        FineDAO fineDAO = DAOFactory.getInstance().getFineDAO();
        try{
            return fineDAO.unpaidFinesCount();
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
