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
    public Order getOrder(int orderId) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            return orderDAO.getOrder(orderId);
        }catch (EntityNotFoundException e){
            throw new OrderNotFoundException(e.getMessage());
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteOrder(int orderId) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            orderDAO.deleteOrder(orderId);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public void addOrder(Order order, String expiryDate, String start, String end) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            OrderValidator.isInputDataValid(order,expiryDate,start,end);
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
    public void changeOrderState(int orderId, String state) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            OrderValidator.isOrderState(state);
            orderDAO.changeOrderState(orderId,Order.OrderState.valueOf(state));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changePaymentState(int orderId, String state) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            OrderValidator.isPaymentState(state);
            orderDAO.changePaymentState(orderId,Order.PaymentState.valueOf(state));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void addDeclineReason(int orderId, String reason) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            orderDAO.addDeclineReason(orderId,reason);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Order> getUserOrders(int userId) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            return orderDAO.getUserOrders(userId);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
