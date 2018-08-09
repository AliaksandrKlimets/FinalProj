package com.epam.car_rental.service.info;

import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface FineService {

    /**
     * Returns {@link List} of {@link Fine} using DAO layer
     * @param begin Begin of selection
     * @param size Selection size
     * @return {@link List} of {@link Fine}
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<Fine> getFines(int begin, int size) throws ServiceException;

    /**
     * Returns {@link List} of User {@link Fine} using DAO layer
     * @param begin Begin of selection
     * @param size Selection size
     * @return {@link List} of User {@link Fine}
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<Fine> getUserFines(String userId, int begin, int size) throws ServiceException;

    /**
     * Change payment state using DAO layer
     * @param fineId Fine id
     * @param state New payment state
     * @param number Page number
     * @throws ServiceException if DAO layer throws DAOException
     */

    void changePaymentState(String fineId, String state, String number) throws ServiceException;

    /**
     * Delete fine using DAO layer
     * @param fineId Fine id
     * @throws ServiceException if DAO layer throws DAOException
     */

    void deleteFine(String fineId) throws ServiceException;

    /**
     * Add fine using DAO layer
     * @param userId User id
     * @param carId Car id
     * @param cause Cause
     * @param bill Repair bill
     * @param dueDate Due date
     * @throws ServiceException if DAO layer throws DAOException
     */

    void addFine(String userId, String carId, String cause, String bill, String dueDate) throws ServiceException;

    /**
     * Returns unpaid Fine list using DAO layer
     * @param begin Begin of selection
     * @param size Selection Size
     * @return unpaid Fine list
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<Fine> getUnpaidFines(int begin, int size) throws ServiceException;

    /**
     * Returns fines count
     * @return fines count
     * @throws ServiceException if DAO layer throws DAOException
     */

    int finesCount() throws ServiceException;

    /**
     * Returns user fines count
     * @param id User id
     * @return user fines count
     * @throws ServiceException if DAO layer throws DAOException
     */

    int userFinesCount(int id) throws ServiceException;

    /**
     * Returns unpaid fines count
     * @return unpaid fines count
     * @throws ServiceException if DAO layer throws DAOException
     */

    int unpaidFinesCount() throws ServiceException;
}
