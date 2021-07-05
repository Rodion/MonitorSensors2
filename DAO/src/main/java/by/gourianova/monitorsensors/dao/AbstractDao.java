package by.gourianova.monitorsensors.dao;
import by.gourianova.monitorsensors.Entity;
import by.gourianova.monitorsensors.exception.DaoException;
import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.db.ConnectionPool;
import by.gourianova.monitorsensors.db.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;


public abstract class AbstractDao<T extends Entity> {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    public abstract Collection<T> findAll() throws DaoException;

    public abstract T findEntityById(Integer id) throws DaoException;

    public abstract boolean createEntity(T entity) throws DaoException;

    public void close(ProxyConnection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        }
    }

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        }
    }

    public abstract boolean deleteEntityById(Integer id) throws DaoException;


    public abstract Entity editEntity(Integer entityId);
}
