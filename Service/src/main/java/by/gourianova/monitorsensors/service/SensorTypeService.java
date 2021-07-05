package by.gourianova.monitorsensors.service;



import by.gourianova.monitorsensors.SensorType;
import by.gourianova.monitorsensors.SensorUnit;
import by.gourianova.monitorsensors.dao.SensorTypeDao;
import by.gourianova.monitorsensors.exception.DaoException;
import by.gourianova.monitorsensors.exception.ServiceException;

import java.io.InputStream;
import java.util.ArrayList;


public class SensorTypeService {
    private SensorTypeDao sensorTypeDao = new SensorTypeDao();

    public ArrayList<SensorType> findAll() throws ServiceException {
        ArrayList<SensorType> typesList;
        try {
            typesList = sensorTypeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
        return typesList;
    }

    public boolean createSensorType(SensorType sensorType) throws ServiceException {
        try {
            return sensorTypeDao.createEntity(sensorType);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in createStation method", e);
        }
    }

    public void create(SensorType sensorType) throws ServiceException {
        try {
            sensorTypeDao.create(sensorType);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in create method", e);
        }
    }
}
