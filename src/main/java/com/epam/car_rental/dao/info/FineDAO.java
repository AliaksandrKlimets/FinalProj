package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.service.info.FineNotFoundException;

import java.util.Date;
import java.util.List;

public interface FineDAO {

    List<Fine> getFines() throws DAOException;

    Fine getUserFine(int userId) throws DAOException, FineNotFoundException;

    void changePaymentState(int fineId) throws DAOException, FineNotFoundException;

    void deleteFine(int fineId) throws DAOException, FineNotFoundException;

    void addFine(int userId, int carId, String cause, double bill, Date dueDate) throws DAOException;

}
