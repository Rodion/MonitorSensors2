package by.gourianova.monitorsensors.action.admin.sensor;

import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.SensorService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

    private SensorService sensorService = new SensorService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        Sensor sensor = new Sensor();
        System.out.println(request.getParameter(TITLE));
        sensor.setName(request.getParameter(TITLE));
        System.out.println(request.getParameter(MODEL));
        sensor.setModel(request.getParameter(MODEL));
        System.out.println(request.getParameter(RANGE_FROM));
        sensor.setRange_from(Integer.parseInt(request.getParameter(RANGE_FROM)));
        System.out.println(request.getParameter(RANGE_TO));
        sensor.setRange_to(Integer.parseInt(request.getParameter(RANGE_TO)));

        System.out.println(request.getParameter(TYPE_ID)+"request.getParameter(TYPE_ID))");
        sensor.setTypeId(Integer.parseInt(request.getParameter(TYPE_ID)));
        System.out.println(request.getParameter(UNIT_ID)+"request.getParameter(UNIT_ID))");
        sensor.setUnitId(Integer.parseInt(request.getParameter(UNIT_ID)));
        System.out.println(request.getParameter(LOCATION));
        sensor.setLocation(request.getParameter(LOCATION));
       /* System.out.println(request.getParameter(DESCRIPTION));
        sensor.setLocation(request.getParameter(DESCRIPTION));*/
        try {
            if (sensorService.createSensor(sensor)) {
                request.setAttribute(MESSAGE, "The sensor is added");
                router.setPagePath(PageConstant.ADMIN_PAGE);
                router.setRoute(Router.RouteType.REDIRECT);
            }

        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
