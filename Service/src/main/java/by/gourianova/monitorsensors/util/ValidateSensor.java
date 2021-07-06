package by.gourianova.monitorsensors.util;

import by.gourianova.monitorsensors.User;

import java.util.regex.Pattern;

import by.gourianova.monitorsensors.Sensor;

import java.util.regex.Pattern;

public class ValidateSensor {


    /**
     * Only digits,- up to 100 characters
     */
    private final static Pattern RANGE_FROM_PATTERN =
            Pattern.compile("([0-9-]{1,100})");

    private final static Pattern RANGE_TO_PATTERN =
            Pattern.compile("([0-9-]{1,100})");
    private final static Pattern RANGE_PATTERN =
            Pattern.compile("([0-9]{1,100})");

    //TODO: validate another properties of the sensor

    private final static String WRONG_RANGE_FROM = "range_from";
    private final static String WRONG_RANGE_TO = "range_to";
    private final static String WRONG_RANGE_DATA = "wrongRangeData";



    public static String validate(Sensor sensor) {
        if (!RANGE_FROM_PATTERN.matcher(String.valueOf(sensor.getRange_from())).matches()) {
            return WRONG_RANGE_FROM;
        }

        if (!RANGE_TO_PATTERN.matcher(String.valueOf(sensor.getRange_from())).matches()) {
            return WRONG_RANGE_TO;
        }
        if (!RANGE_PATTERN.matcher(String.valueOf(sensor.getRange_to() - sensor.getRange_from())).matches()) {
            return WRONG_RANGE_DATA;
        }

        return null;
    }

}