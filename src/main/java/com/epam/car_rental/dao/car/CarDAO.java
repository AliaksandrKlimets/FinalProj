package com.epam.car_rental.dao.car;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.entity.Car;

import java.util.List;

public interface CarDAO {

    /**
     * Returns {@link List} of {@link Car} from database.
     *
     * @param begin begin of list
     * @param size count of items
     *
     * @return {@link List} of {@link Car}
     *
     * @throws DAOException if database error occurred
     */

    List<Car> getCars(int begin, int size) throws DAOException;

    /**
     * Returns Car from database.
     *
     * @param id Car id. Get car by this id.
     *
     * @return {@link Car}
     *
     * @throws DAOException if database error occurred
     */

    Car getCar(int id) throws DAOException;

    /**
     * Delete Car from database.
     *
     * @param id Car id. Delete car by this id.
     *
     * @throws DAOException if database error occurred
     */

    void deleteCar(int id) throws DAOException;

    /**
     * Add Car from database.
     *
     * @param car Car entity with necessary information
     *
     * @throws DAOException if database error occurred
     */

    void addCar(Car car) throws  DAOException;

    /**
     * Returns {@link List} of {@link Car} by type from database.
     *
     * @param begin begin of list
     * @param size count of items
     * @param type Car type. Get car list by this type
     *
     * @return {@link List} of {@link Car}
     *
     * @throws DAOException if database error occurred
     */

    List<Car> getCarsByType(Car.Type type, int begin, int size) throws DAOException;

    /**
     * Returns Car id from database where Car model equals param.
     *
     * @param model Get Car id by this model
     *
     * @return {@link List} of {@link Car}
     *
     * @throws DAOException if database error occurred
     */

    int getCarIdByModel(String model) throws DAOException;

    /**
     * Return items count
     *
     * @return count of items in database
     * @throws DAOException if database error occurred
     */

    int itemsCount() throws DAOException;

    /**
     * Return items count
     *
     * @param type Car type. Get items count by this type
     *
     * @return count of items by type in database
     * @throws DAOException if database error occurred
     */

    int itemsByTypeCount(Car.Type type) throws DAOException;
}
