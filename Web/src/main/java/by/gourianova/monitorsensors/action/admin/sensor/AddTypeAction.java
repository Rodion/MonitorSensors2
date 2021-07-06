package by.gourianova.monitorsensors.action.admin.sensor;

import by.gourianova.monitorsensors.SensorType;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.SensorTypeService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddTypeAction implements Action {

    private final static String TYPE = "type";
    private final static String MESSAGE = "message";

    private SensorTypeService sensorTypeService = new SensorTypeService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        SensorType type = new SensorType();

        type.setType(request.getParameter(TYPE));

        try {
            if (sensorTypeService.createSensorType(type)) {
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
