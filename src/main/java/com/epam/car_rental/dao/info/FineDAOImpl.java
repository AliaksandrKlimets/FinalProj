package com.epam.car_rental.dao.info;

import com.epam.car_rental.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.service.info.FineNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FineDAOImpl implements FineDAO {
    ResourceBundle bundle = ResourceBundle.getBundle("query.info.fine");
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Fine> getFines() throws DAOException {
        return null;
    }

    @Override
    public Fine getUserFine(int userId) throws DAOException, FineNotFoundException {
        return null;
    }

    @Override
    public void changePaymentState(int fineId) throws DAOException, FineNotFoundException {

    }

    @Override
    public void deleteFine(int fineId) throws DAOException, FineNotFoundException {

    }

    @Override
    public void addFine(int userId, int carId, String cause, double bill, Date dueDate) throws DAOException {

    }
}
