package com.epam.car_rental.dao.info;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.util.DAOUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.car_rental.dao.SQLQuery.*;

public class FineDAOImpl implements FineDAO {
    private static final Logger LOGGER = Logger.getLogger(FineDAOImpl.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("query.info.fine");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Fine> getUnpaidFines(int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getUnpaidFines = bundle.getString(FINE_GET_FINES_UNPAID);
            return DAOUtil.createFineListFromDB(getFineSet(getUnpaidFines,begin,size, connection));
        } catch (SQLException e) {
            LOGGER.error("Cannot get unpaid fine list",e);
            throw new DAOException("Cannot get unpaid fine list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Fine> getFines(int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String getFines = bundle.getString(FINE_GET_FINES);
            return DAOUtil.createFineListFromDB(getFineSet(getFines, begin, size, connection));
        } catch (SQLException e) {
            LOGGER.error("Cannot get fine list",e);
            throw new DAOException("Cannot get fine list");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    private ResultSet getFineSet(String query, int begin, int size, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,size);
        statement.setInt(2,begin);
        return statement.executeQuery();
    }

    @Override
    public List<Fine> getUserFines(int userId, int begin, int size) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String userFine = bundle.getString(FINE_GET_USER_FINES);
            PreparedStatement statement = connection.prepareStatement(userFine);
            statement.setInt(1, userId);
            statement.setInt(2, size);
            statement.setInt(3, begin);
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
    public void addFine(Fine fine) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String add = bundle.getString(FINE_ADD_FINE);
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setInt(1, fine.getUserId());
            statement.setInt(2, fine.getCarId());
            statement.setString(3, fine.getCause());
            statement.setDouble(4, fine.getRepairBill());
            statement.setDate(5, fine.getDueDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error while adding fine",e);
            throw new DAOException("Error while adding fine");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int finesCount() throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String count = bundle.getString(FINE_COUNT_FINE);
            return DAOUtil.getCount(count, connection);
        }catch (SQLException e) {
            LOGGER.error("Cannot count fines",e);
            throw new DAOException("Cannot count fines");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int userFinesCount(int id) throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String count = bundle.getString(FINE_COUNT_USER_FINE);
            PreparedStatement statement = connection.prepareStatement(count);
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            int result = 0;
            while(set.next()){
                result = set.getInt(1);
            }
            return result;
        }catch (SQLException e) {
            LOGGER.error("Cannot count unpaid fines",e);
            throw new DAOException("Cannot count unpaid fines");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int unpaidFinesCount() throws DAOException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String count = bundle.getString(FINE_COUNT_UNPAID_FINE);
            return DAOUtil.getCount(count, connection);
        }catch (SQLException e) {
            LOGGER.error("Cannot count unpaid fines",e);
            throw new DAOException("Cannot count unpaid fines");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
