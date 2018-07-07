package com.epam.car_rental.dao.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.UserInfo;
import com.epam.car_rental.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class UserInfoDAOImpl implements UserInfoDAO {

    private ResourceBundle bundle = ResourceBundle.getBundle("query.user");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public UserInfo getUserInfo(int userId) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getInfo = bundle.getString(USER_INFO_GET_INFO);
            PreparedStatement statement = connection.prepareStatement(getInfo);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException("Cannot find user info by id");
            }
            return DAOUtil.createUserInfoFromDB(resultSet);
        } catch (SQLException e) {
            throw new DAOException("SQL error while searching user info");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addUserInfo(int userId, String name, String surname, String email, String phone, Date birthDate) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String addInfo = bundle.getString(USER_INFO_ADD_INFO);
            PreparedStatement statement = connection.prepareStatement(addInfo);
            statement.setInt(1, userId);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, email);
            statement.setString(5, phone);
            statement.setDate(6, birthDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot add user info");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changeName(int id, String name) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String changeName = bundle.getString(USER_INFO_CHANGE_NAME);
            DAOUtil.changeInDB(id, name, changeName, connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot change user name");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changeSurname(int id, String surname) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String changeSurname = bundle.getString(USER_INFO_CHANGE_SURNAME);
            DAOUtil.changeInDB(id, surname, changeSurname, connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot change user surname");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changeEmail(int id, String email) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String changeEmail = bundle.getString(USER_INFO_CHANGE_EMAIL);
            DAOUtil.changeInDB(id, email, changeEmail, connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot change user email");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changePhone(int id, String phone) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String changePhone = bundle.getString(USER_INFO_CHANGE_PHONE);
            DAOUtil.changeInDB(id, phone, changePhone, connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot change user phone");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void deleteUserInfo(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String delete = bundle.getString(USER_INFO_DELETE_INFO);
            DAOUtil.deleteEntity(id,delete,connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot delete user info");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }


}
