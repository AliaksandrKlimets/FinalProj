package com.epam.car_rental.service.car;

import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface CarService {
    /**
     * Returns {@link Car} list from DAO layer
     * @param begin Begin of selection
     * @param size Size of selection
     * @return {@link List} of {@link Car}
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<Car> getCars(int begin, int size) throws ServiceException;

    /**
     *
     * @return items count
     * @throws ServiceException if DAO layer throws DAOException
     */

    int itemsCount() throws ServiceException;

    /**
     *
     * @return items count by type
     * @throws ServiceException if DAO layer throws DAOException
     */

    int itemsByTypeCount(String type) throws ServiceException;

    /**
     * Returns {@link Car} from DAO layer
     * @param id Car id
     * @return Car entity
     * @throws ServiceException
     */

    Car getCar(String id) throws ServiceException;

    /**
     * Use DAO layer to delete {@link Car}
     * @param id Car id. Delete car by this id
     * @throws ServiceException if DAO layer throws DAOException
     */

    void deleteCar(String id) throws ServiceException;

    /**
     * Use DAO layer to add new Car
     * @param car {@link Car} entity
     * @param capacity Car capacity
     * @param carType Car car type
     * @param fuelType Car fuel type
     * @param hour Cost per hour
     * @param oneToSeven Cost from 2 to 7 days
     * @param eightToFifteen Cost from 8 to 15 days
     * @param more Cost from 16+ days
     * @throws ServiceException if DAO layer throws DAOException
     */

    void addCar(Car car, String capacity, String carType, String fuelType, String hour, String oneToSeven, String eightToFifteen, String more) throws ServiceException;

    /**
     * Get Car list from DAO layer
     * @param type Car type
     * @param begin Begin of selection
     * @param size Selection size
     * @return {@link Car} list by type
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<Car> getCarsByType(String type, int begin, int size) throws ServiceException;

    /**
     * Return cat if by model
     * @param model Car model
     * @return Car id my model
     * @throws ServiceException if DAO layer throws DAOException
     */

    int getCarIdByModel(String model) throws ServiceException;

    /**
     * Returns {@link OrderedCar} list from DAO layer
     * @param begin Begin of selection
     * @param size Size of selection
     * @return {@link List} of {@link OrderedCar}
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<OrderedCar> getCarOrders(String id, int begin, int size) throws ServiceException;

    /**
     * Add car to ordered car list using DAO layer
     * @param orderedCar Ordered car
     * @throws ServiceException if DAO layer throws DAOException
     */

    void addCarToOrderedCarList(OrderedCar orderedCar) throws ServiceException;

    /**
     * Delete car from ordered car list using DAO layer
     * @throws ServiceException if DAO layer throws DAOException
     */

    void deleteCarFromOrderedCarList(String carId, String begin, String end) throws ServiceException;

    /**
     * Returns {@link List} of {@link OrderedCar} using DAO layer
     * @param carId Car id
     * @param begin Begin of selection
     * @param size Selection size
     * @return {@link List} of {@link OrderedCar}
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<OrderedCar> getActualCarOrders(String carId, int begin, int size) throws ServiceException;

    /**
     * Check date available
     * @param carId Car id
     *@param begin Begin of rent
     *@param end End of rent
     * @throws ServiceException if DAO layer throws DAOException
     */

    void isDateAvailable(String carId, String begin, String end) throws ServiceException;

    /**
     * Returns car orders count using DAO layer
     * @param id Car order id
     * @return car orders count
     * @throws ServiceException if DAO layer throws DAOException
     */

    int carOrdersCount(int id) throws ServiceException;

    /**
     * Returns actual car orders count using DAO layer
     * @param id Car order id
     * @return actual car orders count
     * @throws ServiceException if DAO layer throws DAOException
     */

    int actualCarOrdersCount(int id) throws ServiceException;
}
