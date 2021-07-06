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


public class EditSensorAction implements Action {
    private final static String SENSOR_ID = "sensorId";
    private final static String SENSOR = "sensor";
    private final static String MESSAGE = "message";
    private SensorService sensorService = new SensorService();


    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {

            Sensor sensor = sensorService.findSensorById(Integer.parseInt(request.getParameter(SENSOR_ID)));
            request.getSession().setAttribute(SENSOR, sensor);
            router.setPagePath(PageConstant.EDIT_SENSOR);
            router.setRoute(Router.RouteType.REDIRECT);

        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
