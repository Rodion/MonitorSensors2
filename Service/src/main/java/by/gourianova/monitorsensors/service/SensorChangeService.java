package by.gourianova.monitorsensors.service;


import by.gourianova.monitorsensors.SensorChange;
import by.gourianova.monitorsensors.dao.SensorChangeDao;
import by.gourianova.monitorsensors.dao.SensorDao;
import by.gourianova.monitorsensors.exception.DaoException;
import by.gourianova.monitorsensors.exception.ServiceException;

import java.util.ArrayList;

public class SensorChangeService {
    private static SensorChangeDao sensorChangeDao = new SensorChangeDao();
    private SensorDao sensorDao = new SensorDao();



    public static SensorChange findEntityById(Integer id) throws ServiceException {
        try {
            return sensorChangeDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findEntityById method", e);
        }
    }

    public ArrayList<SensorChange> findAllUserOrders(Integer userId) throws ServiceException {
        try {
            return sensorChangeDao.findAllUserSensorChanges(userId);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAllUserOrders method", e);
        }
    }

    public ArrayList<SensorChange> findAll() throws ServiceException {
        try {
            return sensorChangeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }

    public ArrayList<SensorChange> findUnclosed() throws ServiceException {
        try {
            return null;
            //sensorChangeDao.findUnclosed();
        } catch (Exception e){//(DaoException e) {
            throw new ServiceException("Transaction failed in findUnclosed method", e);
        }
    }
}
