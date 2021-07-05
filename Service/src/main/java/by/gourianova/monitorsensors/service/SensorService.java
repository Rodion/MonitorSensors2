package by.gourianova.monitorsensors.service;


import by.gourianova.monitorsensors.User;
import by.gourianova.monitorsensors.dao.SensorDao;
import by.gourianova.monitorsensors.exception.DaoException;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.Sensor;

import java.util.ArrayList;


public class SensorService {

    private SensorDao sensorDao = new SensorDao();

    public ArrayList<Sensor> findAll() throws ServiceException {
        ArrayList<Sensor> sensorsList;
        try {
            sensorsList = sensorDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
        return sensorsList;
    }

    public ArrayList<Sensor> findAllByPage(int pageCapacity, int pageNumber) throws ServiceException {
        ArrayList<Sensor> sensorsList;
        try {
            sensorsList = sensorDao.findAllByPage(pageCapacity, pageNumber);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAllByPage method", e);
        }
        return sensorsList;
    }
//TODO is nesessary? fix
    public Sensor editSensor(Integer sensorId, Integer userId) throws ServiceException {
        Sensor sensor;
        sensor = sensorDao.editSensor(sensorId, userId);

        return sensor;
    }
    public Sensor findSensorById(Integer id) throws ServiceException {
        try {
            return sensorDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findUserById method", e);
        }
    }

    public boolean createSensor(Sensor sensor) throws ServiceException {
        try {
           return sensorDao.createEntity(sensor);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in createApp method", e);
        }
    }
}
