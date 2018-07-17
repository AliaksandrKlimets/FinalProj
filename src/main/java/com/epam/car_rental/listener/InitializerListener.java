package com.epam.car_rental.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.dao.connector.ConnectionPoolException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public class InitializerListener implements ServletContextListener {
    public static final Logger LOGGER = Logger.getLogger(ServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        createLogger(servletContextEvent);
        createConnectionPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        destroyConnectionPool();
    }

    private void createConnectionPool() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            String message = "Failed to init connection pool";
            LOGGER.log(Level.FATAL, message, e);
            throw new ConnectionPoolException(message, e);
        }
    }

    private void createLogger(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String location = "log4j-config-location";
        String log4jConfigFile = context.getInitParameter(location);
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);
    }

    private void destroyConnectionPool() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.dispose();
    }
}
