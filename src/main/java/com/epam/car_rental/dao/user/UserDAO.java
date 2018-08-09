package com.epam.car_rental.dao.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.user.UserNotFoundException;

import java.util.List;

public interface UserDAO {

    /**
     * Return List of Users
     *
     * @param begin Begin of selection
     * @param size Selection items count
     * @return {@link List} of {@link User}
     * @throws DAOException if database error occurred
     */
    List<User> getUsers(int begin, int size) throws DAOException;

    /**
     *Return User entity
     *
     * @param login User login
     * @param password User password
     * @return Current User
     * @throws DAOException if database error occurred
     * @throws EntityNotFoundException if {@link java.sql.ResultSet} if empty
     */

    User getUser(String login, String password) throws DAOException;

    /**
     * Change user's login
     * @param userId User id. Change login by this id
     * @param login New login
     * @throws DAOException if database error occurred
     */

    void changeLogin(int userId, String login) throws DAOException;

    /**
     * Change user's password
     * @param userId User id. Change password by this id
     * @param oldPassword Previous password
     * @param newPassword New password
     * @throws DAOException if database error occurred
     */

    void changePassword(int userId, String oldPassword, String newPassword) throws DAOException;

    /**
     * Change user's email
     * @param id User id. Change login by this id
     * @param email New login
     * @throws DAOException if database error occurred
     */

    void changeEmail(int id, String email) throws DAOException;

    /**
     * Change user's phone
     * @param id User id. Change login by this id
     * @param phone New login
     * @throws DAOException if database error occurred
     */

    void changePhone(int id, String phone) throws DAOException;

    /**
     * Add new user
     * @param user Entity of {@link User} with user information
     * @throws DAOException if database error occurred
     */

    void addUser(User user) throws DAOException;

    /**
     * Return User items count
     * @return {@link User} items count
     * @throws DAOException if database error occurred
     */

    int usersCount() throws DAOException;
}
