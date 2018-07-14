package com.epam.car_rental.service.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.DAOFactory;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.dao.info.OrderDAO;
import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.service.validation.validator.OrderValidator;
import com.epam.car_rental.service.validation.validator.Validator;

import java.sql.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getOrders() throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            return orderDAO.getOrders();
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Order getOrder(String orderId) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            Validator.isNumber(orderId);
            return orderDAO.getOrder(Integer.parseInt(orderId));
        }catch (EntityNotFoundException e){
            throw new OrderNotFoundException(e.getMessage());
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void deleteOrder(String orderId) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            Validator.isNumber(orderId);
            orderDAO.deleteOrder(Integer.parseInt(orderId));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }


    @Override
    public void addOrder(Order order,String userId, String expiryDate, String start, String end) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            OrderValidator.isInputDataValid(order,userId,expiryDate,start,end);
            order.setUserId(Integer.parseInt(userId));
            order.setDateOfExpiry(Date.valueOf(expiryDate));
            order.setServiceStart(Date.valueOf(start));
            order.setServiceEnd(Date.valueOf(end));
            orderDAO.addOrder(order);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changeOrderState(String orderId, String state) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            Validator.isNumber(orderId);
            OrderValidator.isOrderState(state);
            orderDAO.changeOrderState(Integer.parseInt(orderId),Order.OrderState.valueOf(state));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changePaymentState(String orderId, String state) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            Validator.isNumber(orderId);
            OrderValidator.isPaymentState(state);
            orderDAO.changePaymentState(Integer.parseInt(orderId),Order.PaymentState.valueOf(state));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void addDeclineReason(String orderId, String reason) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            Validator.isNumber(orderId);
            orderDAO.addDeclineReason(Integer.parseInt(orderId),reason);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public List<Order> getUserOrders(String userId) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            Validator.isNumber(userId);
            return orderDAO.getUserOrders(Integer.parseInt(userId));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }
}
