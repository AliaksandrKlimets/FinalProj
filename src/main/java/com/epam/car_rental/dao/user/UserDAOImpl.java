package com.epam.car_rental.dao.user;

import com.epam.car_rental.DAOException;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.User;

import java.util.List;
import java.util.ResourceBundle;

public class UserDAOImpl implements UserDAO {

    ResourceBundle bundle = ResourceBundle.getBundle("query.user");
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<User> getUsers() throws DAOException {

        return null;
    }

    @Override
    public User getUser(String login, String password) throws DAOException {
        return null;
    }

    @Override
    public void changeLogin(String login) throws DAOException {

    }

    @Override
    public void changePassword(String password, String newPassword) throws DAOException {

    }

    @Override
    public void addUser(String login, String password, User.Role role) throws DAOException, EntityExistException {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public boolean checkLogin(String login) {
        return false;
    }
}
