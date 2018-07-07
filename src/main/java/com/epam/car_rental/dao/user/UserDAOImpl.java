package com.epam.car_rental.dao.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.user.UserExistException;
import com.epam.car_rental.util.DAOUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class UserDAOImpl implements UserDAO {

    private ResourceBundle bundle = ResourceBundle.getBundle("query.user");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<User> getUsers() throws DAOException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String userList = bundle.getString(USER_ALL_USERS);
            PreparedStatement statement = connection.prepareStatement(userList);
            ResultSet resultSet = statement.executeQuery();
            return DAOUtil.createUserListFromBD(resultSet);
        } catch (SQLException e) {
            throw new DAOException("SQL error while search users");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public User getUser(String login, String password) throws DAOException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String user = bundle.getString(USER_SEARCH_USER);
            PreparedStatement statement = connection.prepareStatement(user);
            password = DAOUtil.createPassword(password);
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException("Cannot find user");
            }

            return DAOUtil.createUserFromDB(resultSet);

        } catch (SQLException e) {
            throw new DAOException("SQL error while search users");
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException("Cannot convert password");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changeLogin(int userId, String login) throws DAOException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String change = bundle.getString(USER_UPDATE_LOGIN);
            DAOUtil.changeInDB(userId,login,change,connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot update users login");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changePassword(int userId, String newPassword) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String changePass = bundle.getString(USER_UPDATE_PASSWORD);
            newPassword = DAOUtil.createPassword(newPassword);
            DAOUtil.changeInDB(userId,newPassword,changePass,connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot update users password");
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException("Cannot convert password");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addUser(String login, String password, User.Role role) throws DAOException, UserExistException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String add = bundle.getString(USER_CHECK_LOGIN);
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                throw new UserExistException("User exist");
            }

            password = DAOUtil.createPassword(password);
            addUserToDB(login,password, role, connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot add user to DB");
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException("Cannot convert password");
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    private void addUserToDB(String login, String password, User.Role role, Connection connection) throws SQLException {
        String addToDB = bundle.getString(USER_ADD_USER);
        PreparedStatement statement = connection.prepareStatement(addToDB);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setString(3, role.toString());
        statement.executeUpdate();
    }

    @Override
    public void deleteUser(int id) throws DAOException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String delete = bundle.getString(USER_DELETE_USER);
            DAOUtil.deleteEntity(id,delete,connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot delete user");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}