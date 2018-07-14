package com.epam.car_rental.service.user;

import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface UserService {
    List<User> getUsers() throws ServiceException;

    User getUser(String login, String password) throws ServiceException;

    void changeLogin(int userId, String login) throws ServiceException;

    void changePassword(int userId, String newPassword) throws ServiceException;

    void changeName(int id, String name) throws ServiceException;

    void changeSurname(int id, String surname) throws ServiceException;

    void changeEmail(int id, String email) throws ServiceException;

    void changePhone(int id, String phone) throws ServiceException;

    void addUser(User user, String birthDate) throws ServiceException;

    void deleteUser(int id) throws ServiceException;

    long getUserIdByLogin(String login) throws ServiceException;

    User getUserByLogin(String login) throws ServiceException;

}
