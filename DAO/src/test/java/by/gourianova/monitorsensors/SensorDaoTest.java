package by.gourianova.monitorsensors;

import by.gourianova.monitorsensors.dao.SensorDao;
import by.gourianova.monitorsensors.exception.DaoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class SensorDaoTest {
    private int sensor_Id;
    private String expectedName;

    public SensorDaoTest(int sensors_Id, String expectedNames) {
        this.sensor_Id = sensors_Id;
        this.expectedName = expectedNames;
    }

    @Parameters
    public static Collection<Object[]> multipleTableVales() {
        return Arrays.asList(new Object[][]{

                {74, "Sensor1"},
                {75, "Sensor2"},
                {76, "Sensor3"}

        });
    }

    @Test
    public void findSensorByIdTest001() {
        String actualName = null;
        SensorDao sensorDao = new SensorDao();
        try {

            Sensor sensor = sensorDao.findEntityById(sensor_Id);
            actualName = sensor.getName();
            Assert.assertEquals(expectedName, actualName);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

}