package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.service.info.FineNotFoundException;

import java.sql.Date;
import java.util.List;

public interface FineDAO {

    List<Fine> getFines() throws DAOException;

    List<Fine> getUserFine(int userId) throws DAOException;

    Fine getFine(int fineId) throws DAOException, FineNotFoundException;

    void changePaymentState(int fineId, Fine.State state) throws DAOException;

    void deleteFine(int fineId) throws DAOException;

    void addFine(int userId, int carId, String cause, double bill, Date dueDate) throws DAOException;

    List<Fine> getUnpaidFines()throws DAOException;

}
