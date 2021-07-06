package by.gourianova.monitorsensors.action.admin.sensor;

import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.SensorType;
import by.gourianova.monitorsensors.SensorUnit;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.SensorService;
import by.gourianova.monitorsensors.service.SensorTypeService;
import by.gourianova.monitorsensors.service.SensorUnitService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


public class AddSensorAction implements Action {
    private final static String TITLE = "title";
    private final static String MODEL = "model";
    private final static String RANGE_FROM = "range_from";
    private final static String RANGE_TO = "range_to";
    private final static String TYPE_ID = "typeId";
    private final static String UNIT_ID = "unitId";
    private final static String LOCATION = "location";
    private final static String DESCRIPTION = "description";
    private final static String MESSAGE = "message";
    private final static String WRONG_RANGE_DATA = "wrongRangeData";
    private final static String REFERRER = "referrer";
    // private final static String SENSOR = "sensor";
    private final static String TYPE_LIST = "sensorTypesList";
    private final static String UNIT_LIST = "sensorUnitsList";

    private SensorService sensorService = new SensorService();
    private SensorTypeService sensorTypeService = new SensorTypeService();
    private SensorUnitService sensorUnitService = new SensorUnitService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        Sensor sensor = new Sensor();
        HttpSession session = request.getSession();

        sensor.setName(request.getParameter(TITLE));
        sensor.setModel(request.getParameter(MODEL));
        sensor.setRange_from(Integer.parseInt(request.getParameter(RANGE_FROM)));
        sensor.setRange_to(Integer.parseInt(request.getParameter(RANGE_TO)));
        sensor.setTypeId(Integer.parseInt(request.getParameter(TYPE_ID)));
        sensor.setUnitId(Integer.parseInt(request.getParameter(UNIT_ID)));
        sensor.setLocation(request.getParameter(LOCATION));
        sensor.setLocation(request.getParameter(DESCRIPTION));

        try {
            if (sensorService.validateSensor(sensor) != null) {

                request.setAttribute(WRONG_RANGE_DATA, sensorService.validateSensor(sensor));
                router.setPagePath(PageConstant.ADD_SENSOR);
                request.getSession().setAttribute(REFERRER, PageConstant.ADD_SENSOR);
            } else if (sensorService.createSensor(sensor)) {
               /* Sensor newSensor = sensorService.findSensorById(sensor.getId());
                session.setAttribute(SENSOR, newSensor);*/
                router.setPagePath(PageConstant.ADMIN_PAGE);
                router.setRoute(Router.RouteType.REDIRECT);
            } else {

                request.setAttribute(WRONG_RANGE_DATA, Boolean.TRUE);
                ArrayList<SensorType> sensorTypesList = sensorTypeService.findAll();
                ArrayList<SensorUnit> sensorUnitsList = sensorUnitService.findAll();
                request.setAttribute(TYPE_LIST, sensorTypesList);
                request.setAttribute(UNIT_LIST, sensorUnitsList);
                router.setPagePath(PageConstant.SENSOR_ADD);
               // router.setRoute(Router.RouteType.REDIRECT);
                //TODO: fix to return list
               request.getSession().setAttribute(REFERRER, PageConstant.SENSOR_ADD);
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }

        return router;

    }
}