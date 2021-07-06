package by.gourianova.monitorsensors.dao;


import by.gourianova.monitorsensors.Entity;
import by.gourianova.monitorsensors.SensorUnit;
import by.gourianova.monitorsensors.db.ConnectionPool;
import by.gourianova.monitorsensors.db.ProxyConnection;
import by.gourianova.monitorsensors.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SensorUnitDao extends AbstractDao<SensorUnit> {

    private final static String SQL_FIND_ALL_SENSOR_UNITS = "SELECT * FROM sensor_units;";
    private final static String SQL_CREATE_SENSOR_UNIT = "INSERT INTO sensor_units (unit) VALUES (?);";
    private final static String SQL_FIND_SENSOR_UNIT_BY_ID = "SELECT * FROM sensor_units WHERE id = ?;";
    private final static String SQL_DELETE_UNIT_BY_ID = "DELETE FROM sensor_units WHERE id=?;";

    @Override
    public ArrayList<SensorUnit> findAll() throws DaoException {
        ArrayList<SensorUnit> sensorUnitsList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_SENSOR_UNITS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SensorUnit sensorUnit = new SensorUnit();
                sensorUnit.setId(resultSet.getInt(1));
                sensorUnit.setUnit(resultSet.getString(2));
                sensorUnitsList.add(sensorUnit);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensorUnitsList;
    }

    @Override
    public SensorUnit findEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        SensorUnit sensorUnit = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_SENSOR_UNIT_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sensorUnit = new SensorUnit();
                sensorUnit.setId(resultSet.getInt(1));
                sensorUnit.setUnit(resultSet.getString(2));

            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensorUnit;
    }

    @Override
    public boolean createEntity(SensorUnit entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_SENSOR_UNIT);
            preparedStatement.setString(1, entity.getUnit());
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
            preparedStatement = connection.prepareStatement(SQL_DELETE_UNIT_BY_ID);
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

    //TODO: fix editEntity not sensor
    @Override
    public Entity editEntity(Integer entityId) {
        return null;
    }

    @Override
    public SensorUnit updateEntity(SensorUnit entity) throws DaoException {
        return null;
    }

}
