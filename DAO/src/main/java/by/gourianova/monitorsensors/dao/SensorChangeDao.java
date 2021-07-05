package by.gourianova.monitorsensors.dao;


import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.SensorChange;
import by.gourianova.monitorsensors.User;
import by.gourianova.monitorsensors.db.ConnectionPool;
import by.gourianova.monitorsensors.db.ProxyConnection;
import by.gourianova.monitorsensors.exception.DaoException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SensorChangeDao extends AbstractDao<SensorChange> {

    private final static String SQL_FIND_SENSOR_CHANGE = "SELECT * FROM sensor_changes WHERE Users_Id=? ORDER BY id DESC LIMIT 1;";
    private final static String SQL_FIND_ALL_SENSOR_CHANGES = "SELECT * FROM sensor_changes;";
    //TODO: write if necessary
    //private final static String SQL_FIND_UNCLOSED_SENSOR_CHANGES = "SELECT * FROM sensor_changes WHERE End_Date IS NULL;";
    //private final static String SQL_FIND_UNCLOSED_USER_SENSOR_CHANGE = "SELECT * FROM sensor_changes WHERE Users_Id=? AND End_Date IS NULL;";
    //private final static String SQL_FIND_ALL_USER_SENSOR_CHANGES = "SELECT * FROM sensor_changes WHERE Users_Id=?;";
    //private final static String SQL_CLOSE_SENSOR_CHANGES = "UPDATE sensor_changes SET End_Date=now(), Value=? WHERE Users_Id=? AND End_Date IS NULL;";
    private final static String SQL_UPDATE_USER = "UPDATE users  Roles_Id=2 WHERE Id=?;";
    private final static String SQL_UPDATE_SENSOR = "UPDATE sensorss SET Is_edited=0 WHERE Id=?;";

    @Override
    public ArrayList<SensorChange> findAll() throws DaoException {
        ArrayList<SensorChange> ordersList = new ArrayList<>();
        SensorChange sensorChange;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_SENSOR_CHANGES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sensorChange = buildSensorChange(resultSet);
                ordersList.add(sensorChange);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return ordersList;
    }

    @Override
    public SensorChange findEntityById(Integer userId) throws DaoException {
        SensorChange sensorChange = new SensorChange();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_SENSOR_CHANGE);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sensorChange = buildSensorChange(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensorChange;
    }

    @Override
    public boolean createEntity(SensorChange entity) throws DaoException {
        return false;
    }


    @Override
    public boolean deleteEntityById(Integer id) throws DaoException {
        return false;
    }

    @Override
    public Sensor editSensor(Integer sensorId, Integer userId) {
        return null;
    }


/*    public SensorChange findUnclosedSensorChange(Integer userId) throws DaoException {
        b sensorChange = new SensorChange();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED_USER_SENSOR_CHANGE);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sensorChange.setId(resultSet.getInt(1));
                  sensorChange.setUserId(resultSet.getInt(2));
                sensorChange.setSensorId(resultSet.getInt(3));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findUnclosedSensorChange method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensorChange;
    }
*/

    public ArrayList<SensorChange> findAllUserSensorChanges(Integer userId) throws DaoException {
        ArrayList<SensorChange> ordersList = new ArrayList<>();
       SensorChange sensorChange;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_SENSOR_CHANGES );

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sensorChange = buildSensorChange(resultSet);
                ordersList.add(sensorChange);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAllUserSensorChanges method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return ordersList;
    }
/*
    public ArrayList<SensorChange> findUnclosed() throws DaoException {
        ArrayList<SensorChange> sensorChangesList = new ArrayList<>();
        SensorChange sensorChange;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED__SENSOR_CHANGE );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sensorChange = buildSensorChange(resultSet);
                ordersList.add(sensorChange);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findUnclosed method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return sensorChangesList;
    }
    */

/*
    public void closeSensorChange(User user, Sensor sensor) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_CLOSE_SENSOR_CHANGES);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_SENSOR);
            preparedStatement.setInt(1, sensor.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Error in closeOrder method", e);
            }
            throw new DaoException("Error in closeOrder method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }
*/
    private SensorChange buildSensorChange(ResultSet resultSet) throws SQLException {
        SensorChange sensorChange = new SensorChange();
        sensorChange.setId(resultSet.getInt(1));

        if (!(resultSet.getTimestamp(2) == null)) {
         //   sensorChange.setEndEdit(true);
        }

        sensorChange.setUserId(resultSet.getInt(3));
        sensorChange.setSensorId(resultSet.getInt(4));
        return sensorChange;
    }
}
