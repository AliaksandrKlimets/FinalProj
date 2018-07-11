package com.epam.car_rental.dao.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.user.UserNotFoundException;

import java.util.List;

public interface UserDAO {
    List<User> getUsers() throws DAOException;

    User getUser(String login, String password) throws DAOException;

    void changeLogin(int userId, String login) throws DAOException;

    void changePassword(int userId, String newPassword) throws DAOException;

    void changeName(int id, String name) throws DAOException;

    void changeSurname(int id, String surname) throws DAOException;

    void changeEmail(int id, String email) throws DAOException;

    void changePhone(int id, String phone) throws DAOException;

    void addUser(User user) throws DAOException,EntityExistException;

    void deleteUser(int id) throws DAOException, UserNotFoundException;

}
