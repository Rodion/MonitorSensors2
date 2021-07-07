package by.gourianova.monitorsensors.dao;


import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.db.ConnectionPool;
import by.gourianova.monitorsensors.db.ProxyConnection;
import by.gourianova.monitorsensors.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SensorDao extends AbstractDao<Sensor> {


    // private final static String SQL_FIND_ALL_SENSORS = "SELECT sensors.Id, sensors.Name, sensors.Model, sensors.Range_from, sensors.Range_to, sensor_types.Type, sensor_units.Unit, sensors.Location, sensors.Description FROM sensors, sensor_types, sensor_units WHERE sensors.Type_Id = sensor_types.Id AND sensors.Unit_Id = sensor_units.Id;";
    private final static String SQL_FIND_ALL_SENSORS = "SELECT*  FROM monitorsensors.sensors;";

    private final static String SQL_FIND_BY_PAGE = "SELECT * FROM monitorsensors.sensors ORDER BY sensors.Id LIMIT ? OFFSET ?;";

    private final static String SQL_FIND_BY_ID = "SELECT * FROM sensors WHERE id = ?;";

    //private final static String SQL_FIND_BY_ID = "SELECT sensors.Id , sensors.Name,  sensors.Model,   sensors.Range_from, sensors.Range_to, sensors.Type, sensors.Unit,  sensors.Location, sensors.Description   FROM monitorsensors.sensors WHERE sensors.Id = ?  ORDER BY sensors.Id;";

    //private final static String SQL_FIND_SENSOR = "SELECT sensors.Id ,   sensors.Name,  sensors.Model,   sensors.Range_from, sensors.Range_to,  sensors.Location, sensors.Description  FROM monitorsensors.sensors  WHERE  sensors.Name=?  AND sensors.Model = ?;";
    private final static String SQL_FIND_SENSOR = "SELECT sensors.Id ,   sensors.Name,  sensors.Model,   sensors.Range_from, sensors.Range_to, sensor_types.Type, sensor_units.Unit,  sensors.Location, sensors.Description  FROM monitorsensors.sensors, monitorsensors.sensor_types, monitorsensors.sensor_units  WHERE  sensors.Name=?  AND sensors.Model = ?;";

    private final static String SQL_CREATE_SENSOR = "INSERT INTO  monitorsensors.sensors (Name,  Model,   Range_from, Range_to, Type_Id, Unit_Id,  Location, Description) VALUES (?, ?, ?,?, ?, ?,?, ?);";

    private final static String SQL_DELETE_SENSOR_BY_ID = "DELETE FROM  sensors  WHERE sensors.Id = ?;";

    private final static String SQL_UPDATE_SENSOR = "UPDATE monitorsensors.sensors SET Name=?, Model=?, Range_from=?, Range_to=? Type_Id=?, Unit_Id=?, Location=?, Description=? WHERE Id=?;";

    @Override
    public boolean createEntity(Sensor entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate = false;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_SENSOR);

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getModel());
            preparedStatement.setInt(3, entity.getRange_from());
            preparedStatement.setInt(4, entity.getRange_to());
            preparedStatement.setInt(5, entity.getTypeId());
            preparedStatement.setInt(6, entity.getUnitId());
            //preparedStatement.setString(5, entity.getType());
            //preparedStatement.setString(6, entity.getUnit());
            preparedStatement.setString(7, entity.getLocation());
            preparedStatement.setString(8, entity.getDescription());
            preparedStatement.executeUpdate();
            System.out.println("DAOSensor.createEntity OK ");
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
    public Sensor updateEntity(Sensor sensor) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_SENSOR);
            preparedStatement.setString(1, sensor.getName());
            preparedStatement.setString(2, sensor.getModel());
            preparedStatement.setInt(3, sensor.getRange_from());
            preparedStatement.setInt(4, sensor.getRange_to());
            preparedStatement.setInt(5, sensor.getTypeId());
            preparedStatement.setInt(6, sensor.getUnitId());
            preparedStatement.setString(7, sensor.getLocation());
            preparedStatement.setString(8, sensor.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updateEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensor;
    }

    @Override
    public ArrayList<Sensor> findAll() throws DaoException {
        ArrayList<Sensor> sensorsList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_SENSORS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sensor sensor = buildSensor(resultSet);
                sensorsList.add(sensor);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensorsList;
    }

    @Override
    public Sensor findEntityById(Integer id) throws DaoException {
        Sensor sensor = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            // preparedStatement = connection.prepareStatement(SQL_FIND_SENSOR);
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sensor = buildSensor(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByID method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensor;
    }

    @Override
    public boolean deleteEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        boolean isDeleted = false;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_SENSOR_BY_ID);
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
    public Sensor editEntity(Integer entityId) {
        return null;
    }


    public ArrayList<Sensor> findAllByPage(int pageCapacity, int pageNumber) throws DaoException {
        ArrayList<Sensor> sensorsList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_PAGE);
            preparedStatement.setInt(1, pageCapacity);
            preparedStatement.setInt(2, (pageNumber * pageCapacity - pageCapacity));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sensor sensor = buildSensor(resultSet);
                sensorsList.add(sensor);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAllByPage method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensorsList;
    }

    public  ArrayList<Sensor>  findEntityByTitleAndModel(String name, String model) throws DaoException {
        ArrayList<Sensor> sensorsList = new ArrayList<>();
        Sensor sensor = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_SENSOR);
           // preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sensor = buildSensor(resultSet);
                 sensorsList.add(sensor);
            }


        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByID method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensorsList;
    }


    private Sensor buildSensor(ResultSet resultSet) throws SQLException {
        Sensor sensor = new Sensor();
        sensor.setId(resultSet.getInt(1));
        sensor.setName(resultSet.getString(2));
        sensor.setModel(resultSet.getString(3));
        sensor.setRange_from(resultSet.getInt(4));
        sensor.setRange_to(resultSet.getInt(5));
        //sensor.setTypeId(resultSet.getInt(6));
        //sensor.setUnitId(resultSet.getInt(7));
        sensor.setType(resultSet.getString(6));
        sensor.setUnit(resultSet.getString(7));
        sensor.setLocation(resultSet.getString(8));
        if (resultSet.getString(9) != null) {
            sensor.setDescription(resultSet.getString(9));
        }
        return sensor;
    }


}