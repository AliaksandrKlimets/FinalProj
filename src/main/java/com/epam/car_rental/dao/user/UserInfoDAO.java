package com.epam.car_rental.dao.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.UserInfo;
import com.epam.car_rental.service.user.UserExistException;

import java.util.Date;

public interface UserInfoDAO {
    UserInfo getUserInfo(String login, String password) throws DAOException;

    void changeName(int id, String name) throws DAOException;

    void changeSurname(int id, String surname) throws DAOException;

    void changeEmail(int id, String email) throws DAOException;

    void changePhone(int id, String phone) throws DAOException;

    void deleteUserInfo(int id) throws DAOException;

    void addUserInfo(String name, String surname, String email, String phone, Date birthDate, Date registrationDate) throws DAOException,UserExistException;
}
