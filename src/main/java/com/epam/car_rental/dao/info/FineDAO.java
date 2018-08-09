package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.entity.Fine;

import java.util.List;

public interface FineDAO {

    /**
     * Return Fine list
     * @param begin Begin of selection
     * @param size Size of selection
     * @return Return {@link List} of {@link Fine}
     * @throws DAOException if database error occurred
     */

    List<Fine> getFines(int begin, int size) throws DAOException;

    /**
     * Return User's Fine list
     * @param begin Begin of selection
     * @param size Size of selection
     * @param userId User id. Get Fine list by this id
     * @return Return {@link List} of {@link Fine}
     * @throws DAOException if database error occurred
     */

    List<Fine> getUserFines(int userId, int begin, int size) throws DAOException;

    /**
     * Change fine payment state
     * @param fineId Fine id. Change payment state by this id
     * @param state New payment state
     * @throws DAOException if database error occurred
     */

    void changePaymentState(int fineId, Fine.State state) throws DAOException;

    /**
     * Delete fine by id
     * @param fineId Fine id. Delete fine by this id
     * @throws DAOException if database error occurred
     */

    void deleteFine(int fineId) throws DAOException;

    /**
     * Add new fine
     *
     * @param fine Entity of {@link Fine} with some info
     * @throws DAOException id database error occurred
     */

    void addFine(Fine fine) throws DAOException;

    /**
     * Return Fine list which payment state is UNPAID
     * @param begin Begin of selection
     * @param size Size of selection
     * @return Return {@link List} of {@link Fine}
     * @throws DAOException if database error occurred
     */

    List<Fine> getUnpaidFines(int begin, int size) throws DAOException;

    /**
     * Return fine items count
     * @return count of fine items
     * @throws DAOException if database error occurred
     */

    int finesCount() throws DAOException;

    /**
     * Return user fine items count
     * @return count of user fine items
     * @throws DAOException if database error occurred
     */

    int userFinesCount(int id) throws DAOException;

    /**
     * Return fine items count which payment state is UNPAID
     * @return count of fine items
     * @throws DAOException if database error occurred
     */

    int unpaidFinesCount() throws DAOException;

}
