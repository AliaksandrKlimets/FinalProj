package com.epam.car_rental.dao.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.user.UserExistException;
import com.epam.car_rental.service.user.UserNotFoundException;

import java.util.List;

public interface UserDAO {
    List<User> getUsers() throws DAOException;

    User getUser(String login, String password) throws DAOException;

    void changeLogin(int userId, String login) throws DAOException;

    void changePassword(int userId, String newPassword) throws DAOException;

    void addUser(String login, String password, User.Role role) throws DAOException,UserExistException;

    void deleteUser(int id) throws DAOException, UserNotFoundException;

}
