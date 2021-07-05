package by.gourianova.monitorsensors;
import by.gourianova.monitorsensors.dao.SensorDao;
import by.gourianova.monitorsensors.exception.DaoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;

@RunWith(value= Parameterized.class)
public class SensorDaoTest {
    private int sensor_Id;
    private String expectedName;

    @Parameters
    public static Collection<Object[]> multipleTableVales( ) {
        return Arrays.asList(new Object[][]{

                {1,"Sensor1"},
                {2,"Sensor2"},
                {3,"Sensor3"}

        });
    }
    public  SensorDaoTest(int sensors_Id, String expectedNames){
        this.sensor_Id=sensors_Id;
        this.expectedName=expectedNames;
    }
    @Test
    public void findSensorByIdTest001(){
        String actualName=null;
        SensorDao sensorDao =new SensorDao();
        try {

            Sensor  sensor=sensorDao.findEntityById(sensor_Id);
            actualName=sensor.getName();
            Assert.assertEquals(expectedName, actualName);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

}