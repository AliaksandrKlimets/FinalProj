package com.epam.car_rental.dao.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.UserInfo;
import com.epam.car_rental.service.user.UserExistException;

import java.util.Date;
import java.util.ResourceBundle;

public class UserInfoDAOImpl implements UserInfoDAO {

    ResourceBundle bundle = ResourceBundle.getBundle("query.user");
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public UserInfo getUserInfo(String login, String password) throws DAOException {
        return null;
    }

    @Override
    public void addUserInfo(String name, String surname, String email, String phone, Date birthDate, Date registrationDate)throws DAOException,UserExistException {

    }

    @Override
    public void changeName(int id, String name) throws DAOException {

    }

    @Override
    public void changeSurname(int id, String surname) throws DAOException {

    }

    @Override
    public void changeEmail(int id, String email) throws DAOException {

    }

    @Override
    public void changePhone(int id, String phone) throws DAOException {

    }

    @Override
    public void deleteUserInfo(int id) throws DAOException {

    }
}
