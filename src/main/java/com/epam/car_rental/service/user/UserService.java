package com.epam.car_rental.service.user;

import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface UserService {

    /**
     * Returns {@link List} of {@link User}
     * @param begin Begin of selection
     * @param size Selection size
     * @return {@link List} of {@link User}
     * @throws ServiceException if DAO layer throws DAOException
     */

    List<User> getUsers(int begin, int size) throws ServiceException;

    /**
     * Returns {@link User} entity
     * @param login User login
     * @param password User password
     * @return {@link User} entity
     * @throws ServiceException if DAO layer throws DAOException
     */

    User getUser(String login, String password) throws ServiceException;

    /**
     * Change user login using DAO layer
     * @param userId User id
     * @param login new login
     * @throws ServiceException if DAO layer throws DAOException
     */

    void changeLogin(String userId, String login) throws ServiceException;

    /**
     * Change user password using DAO layer
     * @param userId User id
     * @param newPassword new password
     * @param oldPassword old password
     * @throws ServiceException if DAO layer throws DAOException
     */

    void changePassword(String userId,String oldPassword, String newPassword) throws ServiceException;

    /**
     * Change user email using DAO layer
     * @param id User id
     * @param email new email
     * @throws ServiceException if DAO layer throws DAOException
     */

    void changeEmail(String id, String email) throws ServiceException;

    /**
     * Change user phone using DAO layer
     * @param id User id
     * @param phone new phone
     * @throws ServiceException if DAO layer throws DAOException
     */

    void changePhone(String id, String phone) throws ServiceException;

    /**
     * Add new {@link User} using DAO layer
     * @param user User entity with info
     * @param birthDate birth date
     * @throws ServiceException if DAO layer throws DAOException
     */

    void addUser(User user, String birthDate) throws ServiceException;

    /**
     * Returns users count
     * @return users count
     * @throws ServiceException if DAO layer throws DAOException
     */

    int usersCount() throws ServiceException;

}
