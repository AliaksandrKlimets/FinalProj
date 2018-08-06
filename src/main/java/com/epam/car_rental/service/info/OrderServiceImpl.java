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
    public List<Order> getOrders(int begin, int size) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            return orderDAO.getOrders(begin,size);
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
    public void deleteOrder(String orderId, String userId) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            Validator.isNumber(orderId);
            Validator.isNumber(userId);
            orderDAO.deleteOrder(Integer.parseInt(orderId), Integer.parseInt(userId));
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }


    @Override
    public void addOrder(Order order,String userId, String carId, String expiryDate, String start, String end) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            OrderValidator.isInputDataValid(order,userId,carId,expiryDate,start,end);
            order.setUserId(Integer.parseInt(userId));
            order.setCarId(Integer.parseInt(carId));
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
            orderDAO.changeOrderState(Integer.parseInt(orderId),Order.OrderState.valueOf(state.toUpperCase()));
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
            orderDAO.changePaymentState(Integer.parseInt(orderId),Order.PaymentState.valueOf(state.toUpperCase()));
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
    public List<Order> getUserOrders(String userId, int begin, int size) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            Validator.isNumber(userId);
            return orderDAO.getUserOrders(Integer.parseInt(userId), begin, size);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public int ordersCount() throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            return orderDAO.ordersCount();
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int userOrdersCount(int id) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            return orderDAO.userOrdersCount(id);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Order> getNewOrders(int begin, int size) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            return orderDAO.getNewOrders(begin,size);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int newOrderCount() throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            return orderDAO.newOrderCount();
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
