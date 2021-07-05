package by.gourianova.monitorsensors.service;

import by.gourianova.monitorsensors.User;
import by.gourianova.monitorsensors.dao.UserDao;
import by.gourianova.monitorsensors.exception.DaoException;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.util.MD5;
import by.gourianova.monitorsensors.util.ValidateUser;

import java.util.ArrayList;


public class UserService {

    private final UserDao userDao = new UserDao();


    public User findUserByLoginAndPassword(String login, String password) throws ServiceException {
        password = MD5.md5Encode(password);
        try {
            return userDao.findEntityByLoginAndPassword(login, password);

        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findUserByLoginAndPassword method", e);
        }
    }

    public boolean registerUser(User user) throws ServiceException {

        user.setPassword(MD5.md5Encode(user.getPassword()));

        try {
//TODO test and fix            return !userDao.findEntityByLogin(user.getLogin()) && userDao.createEntity(user);
            return userDao.createEntity(user);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in registerUser method", e);
        }
    }

    public User findUserById(Integer id) throws ServiceException {
        try {
            return userDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findUserById method", e);
        }
    }


    public ArrayList<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }

    public void updateUser(User user) throws ServiceException {
        try {
            userDao.updateEntity(user);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in updateUser method", e);
        }
    }

    public String validateUser(User user) throws ServiceException {

        return ValidateUser.validate(user);
    }


    public boolean deleteEntityById(Integer id) throws ServiceException {
        boolean isDeleted = false;
        try {
            isDeleted = userDao.deleteEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in deleteUser method", e);
        }

        return isDeleted;
    }
}