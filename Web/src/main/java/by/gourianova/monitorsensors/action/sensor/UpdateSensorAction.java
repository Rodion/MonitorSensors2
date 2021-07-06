package by.gourianova.monitorsensors.action.sensor;

import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.SensorService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class UpdateSensorAction implements Action {
    private final static String TITLE = "title";
    private final static String MODEL = "model";
    private final static String RANGE_FROM = "range_from";
    private final static String RANGE_TO = "range_to";
    private final static String TYPE_ID = "typeId";
    private final static String UNIT_ID = "unitId";
    private final static String LOCATION = "location";
    private final static String DESCRIPTION = "description";
    private final static String MESSAGE = "message";
    private final static String SENSOR_ID = "sensorId";
    private SensorService sensorService = new SensorService();

    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        Sensor sensor = new Sensor();

        if (request.getParameter(SENSOR_ID) != null) {
            sensor.setId(Integer.parseInt(request.getParameter(SENSOR_ID)));
        }
        sensor.setName(request.getParameter(TITLE));
        sensor.setModel(request.getParameter(MODEL));
        sensor.setRange_from(Integer.parseInt(request.getParameter(RANGE_FROM)));
        sensor.setRange_to(Integer.parseInt(request.getParameter(RANGE_TO)));
        sensor.setTypeId(Integer.parseInt(request.getParameter(TYPE_ID)));
        sensor.setUnitId(Integer.parseInt(request.getParameter(UNIT_ID)));
        try {
            sensor.setId(Integer.parseInt(request.getParameter(SENSOR_ID)));
            sensorService.updateSensor(sensor);
            router.setPagePath(PageConstant.ADMIN_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;

}}