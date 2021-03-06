package com.epam.car_rental.dao.connector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.car_rental.dao.connector.ConnectorConst.*;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private static volatile ConnectionPool instance;

    private ConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_NAME);

        this.driverName = bundle.getString(DRIVER);
        this.user = bundle.getString(USER);
        this.password = bundle.getString(PASSWORD);
        this.url = bundle.getString(URL);

        String poolSizeStr = bundle.getString(DB_POOL_SIZE);
        Matcher matcher = Pattern.compile("\\d+").matcher(poolSizeStr);
        this.poolSize = matcher.matches() ? Integer.parseInt(poolSizeStr) : 5;
    }

    /**
     * Returns {@link ConnectionPool} instance.
     *
     * @return instance of connection pool
     */

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Initializes Connection Pool.
     *
     * @throws ConnectionPoolException
     * if catch {@link SQLException} or {@link ClassNotFoundException}
     */

    public void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            givenAwayQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(connection);
            }
        } catch (SQLException e) {
            final String massage = "SQL Exception: cannot ger connection";
            LOGGER.error(massage, e);
            throw new ConnectionPoolException(massage, e);
        } catch (ClassNotFoundException e) {
            final String massage = "Cannot find connection driver class";
            LOGGER.error(massage, e);
            throw new ConnectionPoolException(massage, e);
        }
    }

    /** Disposes connection pool.
     *
     * @throws ConnectionPoolException
     * if error occurred while connecting to the sql server
     */

    public void dispose() throws ConnectionPoolException{
        clearConnectionQueue();
    }

    /**
     * Clears connection pool.
     *
     * @throws ConnectionPoolException
     * if catch {@link SQLException}
     */

    private void clearConnectionQueue() throws ConnectionPoolException{
        try{
            closeConnectionQueue(givenAwayQueue);
            closeConnectionQueue(connectionQueue);
        }catch (SQLException e){
            final String massage = "Closing connection error";
            LOGGER.error(massage,e);
            throw new ConnectionPoolException(massage,e);
        }
    }

    /**
     * Closes connection queue.
     *
     * @param queue
     * connection queue to close
     *
     * @throws SQLException
     */

    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException{
        Connection connection;

        while((connection=queue.poll())!=null){
            if(!connection.getAutoCommit()){
                connection.getAutoCommit();
            }
            connection.close();
        }
    }

    /**
     * Returns {@link Connection} from connection pool.
     *
     * @return {@link Connection} from {@link ConnectionPool}
     *
     * @throws ConnectionPoolException
     * if catch {@link InterruptedException} or {@link SQLException}
     */

    public Connection getConnection() throws ConnectionPoolException{
        Connection connection;
        try {
            connection = connectionQueue.take();
            if(connection.isClosed()){
                connection= reopenConnection();
            }
            givenAwayQueue.add(connection);
            return  connection;
        }catch (InterruptedException e){
            final String massage = "Error connecting to data source";
            LOGGER.error(massage,e);
            throw new ConnectionPoolException(massage,e);
        }catch (SQLException e){
            final String massage = "Error while reopening connection";
            LOGGER.error(massage,e);
            throw new ConnectionPoolException(massage,e);
        }
    }

    /**
     * Closes current connection.
     *
     * @param connection
     * connection to close
     *
     * @throws ConnectionPoolException
     * if impossible to delete connection from the given away connections pool
     */

    public void closeConnection(Connection connection) throws ConnectionPoolException{
        if(!givenAwayQueue.remove(connection)){
            final String massage = "Error deleting connection from given away queue";
            LOGGER.log(Level.ERROR,massage);
            throw new ConnectionPoolException(massage);
        }

        try{
            if(connection.isClosed()){
                connection = reopenConnection();
            }
            if(connection.isReadOnly()){
                connection.setReadOnly(false);
            }
        }catch (SQLException e){
            final String massage = "Cannot access connection";
            LOGGER.error(massage,e);
            throw new ConnectionPoolException(massage,e);
        }

        if(!connectionQueue.offer(connection)){
            final String massage = "Error allocating connection in the poll";
            throw new ConnectionPoolException(massage);
        }
    }

    /**
     * Reopens connection.
     * Use {@link DriverManager}.
     *
     * @return reopened {@link Connection}
     * @throws SQLException if cannot reopen connection
     */

    private Connection reopenConnection() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}
