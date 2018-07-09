package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.service.info.FineNotFoundException;
import com.epam.car_rental.util.DAOUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class FineDAOImpl implements FineDAO {
    private static final Logger LOGGER = Logger.getLogger(FineDAOImpl.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("query.info.fine");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Fine> getUnpaidFines() throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getUnpaidFines = bundle.getString(FINE_GET_FINES_UNPAID);
            return DAOUtil.createFineListFromDB(getFineSet(getUnpaidFines, connection));
        } catch (SQLException e) {
            LOGGER.error("Cannot get unpaid fine list",e);
            throw new DAOException("Cannot get unpaid fine list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Fine> getFines() throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getFines = bundle.getString(FINE_GET_FINES);
            return DAOUtil.createFineListFromDB(getFineSet(getFines, connection));
        } catch (SQLException e) {
            LOGGER.error("Cannot get fine list",e);
            throw new DAOException("Cannot get fine list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    private ResultSet getFineSet(String query, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    @Override
    public Fine getFine(int fineId) throws DAOException, FineNotFoundException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getFine = bundle.getString(FINE_GET_FINE);
            PreparedStatement statement = connection.prepareStatement(getFine);
            statement.setInt(1, fineId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                LOGGER.error("Cannot find fine by id");
                throw new FineNotFoundException("Cannot find fine by id");
            }

            return DAOUtil.createFineFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting fine by id",e);
            throw new DAOException("Error while getting fine by id");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Fine> getUserFine(int userId) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String userFine = bundle.getString(FINE_GET_USER_FINES);
            PreparedStatement statement = connection.prepareStatement(userFine);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return DAOUtil.createFineListFromDB(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while getting fine list by user id",e);
            throw new DAOException("Error while getting fine list by user id");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changePaymentState(int fineId, Fine.State state) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String change = bundle.getString(FINE_CHANGE_PAYMENT_STATE);
            DAOUtil.changeInDB(fineId, state.toString(), change, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while changing state",e);
            throw new DAOException("Error while changing state");
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    @Override
    public void deleteFine(int fineId) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String delete = bundle.getString(FINE_DELETE_FINE);
            DAOUtil.deleteEntity(fineId, delete, connection);
        } catch (SQLException e) {
            LOGGER.error("Error while deleting fine",e);
            throw new DAOException("Error while deleting fine");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addFine(int userId, int carId, String cause, double bill, Date dueDate) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String add = bundle.getString(FINE_ADD_FINE);
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setInt(1, userId);
            statement.setInt(2, carId);
            statement.setString(3, cause);
            statement.setDouble(4, bill);
            statement.setDate(5, dueDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error while adding fine",e);
            throw new DAOException("Error while adding fine");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
