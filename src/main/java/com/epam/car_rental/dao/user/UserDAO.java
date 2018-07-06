package com.epam.car_rental.dao.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers() throws DAOException;

    User getUser(String login, String password) throws DAOException;

    void changeLogin(String login) throws DAOException;

    void changePassword(String password, String newPassword) throws DAOException;

    void addUser(String login, String password, User.Role role) throws DAOException,EntityExistException;

    void deleteUser(int id);

    boolean checkLogin(String login);
}
