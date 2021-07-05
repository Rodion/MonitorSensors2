package by.gourianova.monitorsensors.action.admin.sensor;


import by.gourianova.monitorsensors.SensorUnit;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.SensorUnitService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class ShowAllUnitsAction implements Action {
    private final static String SENSOR_UNIT = "sensorUnitsList";
    private final static String MESSAGE = "message";
    private SensorUnitService sensorUnitService = new SensorUnitService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<SensorUnit> unitsList;
        try {
            unitsList = sensorUnitService.findAll();
            request.setAttribute(SENSOR_UNIT, unitsList);
            router.setPagePath(PageConstant.ALL_SENSOR_UNITS);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
