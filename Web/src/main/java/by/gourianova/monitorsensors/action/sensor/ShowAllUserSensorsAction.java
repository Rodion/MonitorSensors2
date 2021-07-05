package by.gourianova.monitorsensors.action.sensor;

import by.gourianova.monitorsensors.SensorType;
import by.gourianova.monitorsensors.SensorUnit;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.SensorService;
import by.gourianova.monitorsensors.service.SensorTypeService;
import by.gourianova.monitorsensors.service.SensorUnitService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class ShowAllUserSensorsAction implements Action {
    private final static String SENSORS_LIST = "sensorsList";

    private final static String MESSAGE = "message";
    private SensorService sensorService = new SensorService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<Sensor> sensorsList;
        try {
            sensorsList = sensorService.findAll();
            request.setAttribute(SENSORS_LIST, sensorsList);
            router.setPagePath(PageConstant.MAIN_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
