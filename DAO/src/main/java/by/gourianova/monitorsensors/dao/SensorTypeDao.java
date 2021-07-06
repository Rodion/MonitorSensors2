package by.gourianova.monitorsensors.dao;


import by.gourianova.monitorsensors.Entity;
import by.gourianova.monitorsensors.SensorType;
import by.gourianova.monitorsensors.db.ConnectionPool;
import by.gourianova.monitorsensors.db.ProxyConnection;
import by.gourianova.monitorsensors.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SensorTypeDao extends AbstractDao<SensorType> {
    private final static String SQL_FIND_ALL_TYPES = "SELECT * FROM sensor_types;";
    private final static String SQL_CREATE = "INSERT INTO sensor_types (Type) VALUES(?);";
    private final static String SQL_FIND_TYPE_BY_ID = "SELECT * FROM sensor_types WHERE id = ?;";
    private final static String SQL_DELETE_TYPE_BY_ID = "DELETE FROM sensor_types WHERE id=?;";
    private final static String SQL_EDIT_TYPE = "INSERT INTO sensor_types (Type) VALUES(?) WHERE id = ?;";
    private final static String SQL_CREATE_SENSOR_TYPE = "INSERT INTO sensor_types (type) VALUES (?);";

    @Override
    public ArrayList<SensorType> findAll() throws DaoException {
        ArrayList<SensorType> typesList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_TYPES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SensorType sensorType = new SensorType();
                sensorType.setId(resultSet.getInt(1));
                sensorType.setType(resultSet.getString(2));

                typesList.add(sensorType);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return typesList;
    }

    @Override
    public SensorType findEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        SensorType sensorType = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_TYPE_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sensorType = new SensorType();
                sensorType.setId(resultSet.getInt(1));
                sensorType.setType(resultSet.getString(2));

            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensorType;
    }

    public void create(SensorType entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE);
            preparedStatement.setString(1, entity.getType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in create method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public SensorType updateEntity(SensorType entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_EDIT_TYPE);
            preparedStatement.setString(1, entity.getType());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updateEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
   return entity; }

    @Override
    public boolean createEntity(SensorType entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_SENSOR_TYPE);
            preparedStatement.setString(1, entity.getType());
            preparedStatement.executeUpdate();
            isCreate = true;
        } catch (SQLException e) {
            throw new DaoException("Error in createEntity", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isCreate;
    }

    @Override
    public boolean deleteEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        boolean isDeleted = false;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_TYPE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            isDeleted = true;
        } catch (SQLException e) {
            throw new DaoException("Error in deleteEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isDeleted;
    }

    @Override
    public Entity editEntity(Integer entityId) {
        return null;
    }

}