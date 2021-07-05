package by.gourianova.monitorsensors.service;



import by.gourianova.monitorsensors.SensorUnit;
import by.gourianova.monitorsensors.dao.SensorUnitDao;
import by.gourianova.monitorsensors.exception.DaoException;
import by.gourianova.monitorsensors.exception.ServiceException;

import java.util.ArrayList;

public class SensorUnitService {

    private SensorUnitDao sensorUnitDao = new SensorUnitDao();

    public ArrayList<SensorUnit> findAll() throws ServiceException {
        try {
            return sensorUnitDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }

    public boolean createSensorUnit(SensorUnit sensorUnit) throws ServiceException {
        try {
            return sensorUnitDao.createEntity(sensorUnit);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in createStation method", e);
        }
    }
    public boolean deleteEntityById(int id) throws ServiceException {

        try { return  sensorUnitDao.deleteEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in delete method", e);
        }


    }
}
