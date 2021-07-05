package by.gourianova.monitorsensors.action.admin.sensor;

import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.SensorUnit;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.SensorService;
import by.gourianova.monitorsensors.service.SensorUnitService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddUnitAction implements Action {

    private final static String UNIT = "unit";
    private final static String MESSAGE = "message";

    private SensorUnitService sensorUnitService = new SensorUnitService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        SensorUnit unit = new SensorUnit();

        unit.setUnit(request.getParameter(UNIT));

        try {
            if (sensorUnitService.createSensorUnit(unit)) {
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
