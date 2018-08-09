package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.OrderedCar;

import java.sql.Date;
import java.util.List;

public interface OrderedCarDAO {
    /**
     *Return list of current car orders
     *
     * @param id Car id. Get car orders by this id
     * @param begin Begin of selection
     * @param size Items count in selection
     * @return {@link List} of {@link OrderedCar}
     * @throws DAOException if database error occurred
     */

    List<OrderedCar> getCarOrders(int id, int begin, int size) throws DAOException;

    /**
     * Add car to ordered car list
     *
     * @param orderedCar Entity of {@link OrderedCar}
     * @throws DAOException if database error occurred
     */

    void addCarToOrderedCarList(OrderedCar orderedCar) throws DAOException;

    /**
     * Delete car order
     *
     * @param carId Delete car by this id
     * @param begin Begin of rent
     * @param end End of rent
     * @throws DAOException if database error occurred
     */

    void deleteCarFromOrderedCarList(int carId, String begin, String end) throws DAOException;

    /**
     *Return list of current car orders which are actual
     *
     * @param carId Car id. Get car orders by this id
     * @param begin Begin of selection
     * @param size Items count in selection
     * @return {@link List} of {@link OrderedCar}
     * @throws DAOException if database error occurred
     */

    List<OrderedCar> getActualCarOrders(int carId, int begin, int size) throws DAOException;

    /**
     * Check is date available
     *
     * @param carId Car id. Check date by this id
     * @param begin Begin of rent
     * @param end End of rent
     * @throws DateNotAvailableException if date is not available
     */

    void isDateAvailable(int carId, Date begin, Date end) throws DAOException;

    /**
     * Return car orders count
     *
     * @param id Count items by this id
     * @return count of car orders
     * @throws DAOException if database error occurred
     */

    int carOrdersCount(int id) throws DAOException;

    /**
     * Return actual car orders count
     *
     * @param id Count items by this id which are actual
     * @return count of car orders
     * @throws DAOException if database error occurred
     */

    int actualCarOrdersCount(int id) throws DAOException;
}
