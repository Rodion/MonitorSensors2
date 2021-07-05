package by.gourianova.monitorsensors.dao;

import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.User;
import by.gourianova.monitorsensors.db.ConnectionPool;
import by.gourianova.monitorsensors.db.ProxyConnection;
import by.gourianova.monitorsensors.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao extends AbstractDao<User> {
    private final static String SQL_CREATE_USER = "INSERT INTO monitorsensors.users (Login, Password, First_Name, Last_Name,  Roles_Id) VALUES (?, ?, ?, ?, ?);";
    private final static String SQL_FIND_ALL_USERS = "SELECT * FROM monitorsensors.users;";
    private final static String SQL_FIND_USER_BY_ID = "SELECT * FROM monitorsensors.users WHERE id = ?;";
    private final static String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * FROM monitorsensors.users WHERE login = ? AND password = ?;";
    private final static String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM monitorsensors.users WHERE login = ?;";
    private final static String SQL_UPDATE_ROLE = "UPDATE monitorsensors.users SET Roles_Id=? WHERE Id=?;";
    private final static String SQL_DELETE_USER_BY_ID = "DELETE FROM monitorsensors.users WHERE id=?;";
    private final static String SQL_UPDATE_PASSWORD = "UPDATE monitorsensors.users SET Login=?, Password=?  WHERE Id=?;";


    @Override
    public boolean createEntity(User user) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreated;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setInt(5, new Integer(7));
            preparedStatement.executeUpdate();
            isCreated = true;
        } catch (SQLException e) {
            throw new DaoException("Error in createEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isCreated;
    }
    public User findEntityByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
            try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByLoginAndPassword method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public ArrayList<User> findAll() throws DaoException {
        ArrayList<User> usersList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersList.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return usersList;
    }

    @Override
    public User findEntityById(Integer id) throws DaoException {
        User user = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public boolean deleteEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        boolean isDeleted = false;
        PreparedStatement preparedStatement = null;
        connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
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
    public Sensor editSensor(Integer sensorId, Integer userId) {
        return null;
    }



    public boolean findEntityByLogin(String login) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByLogin method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public User updateEntity(User user) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ROLE);
            preparedStatement.setInt(1, user.getRoleId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updateEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }



    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setLogin(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setFirstName(resultSet.getString(4));
        user.setLastName(resultSet.getString(5));
        user.setRoleId(resultSet.getInt(6));
        return user;
    }



}

