package com.epam.car_rental.dao.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.util.DAOUtil;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
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
                LOGGER.error("Cannot find user");
                throw new EntityNotFoundException("Cannot find user");
            }

            return DAOUtil.createUserFromDB(resultSet);

        } catch (SQLException e) {
            LOGGER.error("SQL error while search users", e);
            throw new DAOException("SQL error while search users");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Cannot convert password", e);
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
            DAOUtil.changeInDB(userId, login, change, connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot update users login", e);
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
            DAOUtil.changeInDB(userId, newPassword, changePass, connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot update users password", e);
            throw new DAOException("Cannot update users password");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Cannot convert password", e);
            throw new DAOException("Cannot convert password");
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
            LOGGER.error("Cannot  change user name", e);
            throw new DAOException("Cannot  change user name");
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
            LOGGER.error("Cannot  change user surname", e);
            throw new DAOException("Cannot  change user surname");
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
            LOGGER.error("Cannot  change user email", e);
            throw new DAOException("Cannot  change user email");
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
            LOGGER.error("Cannot  change user phone", e);
            throw new DAOException("Cannot  change user phone");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addUser(User user) throws DAOException, EntityExistException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String add = bundle.getString(USER_CHECK_LOGIN);
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                LOGGER.error("User exist");
                throw new EntityExistException("User exist");
            }

            user.setPassword(DAOUtil.createPassword(user.getPassword()));
            addUserToDB(user, connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot add user to DB", e);
            throw new DAOException("Cannot add user to DB");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Cannot convert password", e);
            throw new DAOException("Cannot convert password");
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    private void addUserToDB(User user, Connection connection) throws SQLException {
        String addUserToDB = bundle.getString(USER_ADD_USER);
        String addUserInfoToDB = bundle.getString(USER_INFO_ADD_INFO);
        String getId = bundle.getString(USER_GET_ID);
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(addUserToDB);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole().toString());
        statement.executeUpdate();


        statement = connection.prepareStatement(getId);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        ResultSet set = statement.executeQuery();

        statement = connection.prepareStatement(addUserInfoToDB);
        statement.setInt(1, set.getInt(1));
        statement.setString(2, user.getName());
        statement.setString(3, user.getSurname());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPhone());
        statement.setDate(6, user.getBirthDate());
        statement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public void deleteUser(int id) throws DAOException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String delete = bundle.getString(USER_DELETE_USER);
            DAOUtil.deleteEntity(id, delete, connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot delete user", e);
            throw new DAOException("Cannot delete user");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}