package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.entity.Fine;

import java.util.List;

public interface FineDAO {

    List<Fine> getFines() throws DAOException;

    List<Fine> getUserFines(int userId) throws DAOException;

    Fine getFine(int fineId) throws DAOException, EntityNotFoundException;

    void changePaymentState(int fineId, Fine.State state) throws DAOException;

    void deleteFine(int fineId) throws DAOException;

    void addFine(Fine fine) throws DAOException;

    List<Fine> getUnpaidFines()throws DAOException;

}
