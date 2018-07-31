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
    public List<User> getUsers(int begin, int size) throws DAOException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String userList = bundle.getString(USER_ALL_USERS);
            PreparedStatement statement = connection.prepareStatement(userList);
            statement.setInt(1,size);
            statement.setInt(2,begin);
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
            String add = bundle.getString(USER_CHECK_LOGIN);
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1, login);
            statement.setString(2, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                LOGGER.error("Login exist, Cannot change login");
                throw new EntityExistException("Login exist, Cannot change login");
            }

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
            String add = bundle.getString(USER_CHECK_LOGIN);
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1, email);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                LOGGER.error("Email exist, cannot change email");
                throw new EntityExistException("Email exist, cannot change email");
            }
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
    public void addUser(User user) throws DAOException {
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

    @Override
    public long getUserIdByLogin(String login) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getId = bundle.getString(USER_GET_ID);
            PreparedStatement statement = connection.prepareStatement(getId);
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();

            if (!set.isBeforeFirst()) {
                LOGGER.error("Login do not exist");
                throw new EntityNotFoundException("Login don't exist");
            }

            return set.getLong(1);
        } catch (SQLException e) {
            LOGGER.error("Cannot find user id by login");
            throw new DAOException("Cannot find user id by login");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    public User getUserByLogin(String login) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getUser = bundle.getString(USER_GET_USER_BY_LOGIN);
            PreparedStatement statement = connection.prepareStatement(getUser);
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();

            if (!set.isBeforeFirst()) {
                LOGGER.error("User do not exist");
                throw new EntityNotFoundException("User don't exist");
            }

            return DAOUtil.createUserFromDB(set);
        } catch (SQLException e) {
            LOGGER.error("Cannot find user by login");
            throw new DAOException("Cannot find user  by login");
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
        ResultSet set = statement.executeQuery();
        int id=0;
        while(set.next()){
           id=set.getInt(1);
        }
        statement = connection.prepareStatement(addUserInfoToDB);
        statement.setInt(1, id);
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

    @Override
    public int usersCount() throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String count = bundle.getString(USER_COUNT_ITEMS);
            return DAOUtil.getCount(count,connection);
        }catch (SQLException e){
            LOGGER.error("Cannot count users");
            throw new DAOException("Cannot count users");
        }finally {
            connectionPool.closeConnection(connection);
        }
    }
}