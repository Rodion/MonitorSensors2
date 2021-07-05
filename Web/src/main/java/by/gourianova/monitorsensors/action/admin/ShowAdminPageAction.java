package by.gourianova.monitorsensors.action.admin;

import by.gourianova.monitorsensors.*;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.*;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShowAdminPageAction implements Action {
    private final static String USERS_LIST = "usersList";
    private final static String SENSORS_LIST = "sensorsList";
    private final static String SENSORS_UNITS_LIST = "sensorUnitsList";
    private final static String SENSORS_TYPES_LIST = "sensorTypesList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();
    private SensorService sensorsService = new SensorService();
    private SensorTypeService sensorTypeService = new SensorTypeService();
    private SensorUnitService sensorUnitService = new SensorUnitService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<User> usersList;
        ArrayList<Sensor> sensorsList;
        ArrayList<SensorType> sensorTypesList;
        ArrayList<SensorUnit> sensorUnitsList;
        try {
            usersList = userService.findAll();
            sensorsList = sensorsService.findAll();
            sensorUnitsList =  sensorUnitService.findAll();
            sensorTypesList =  sensorTypeService.findAll();
            request.setAttribute(USERS_LIST, usersList);
            request.setAttribute(SENSORS_LIST, sensorsList);
            request.setAttribute(SENSORS_UNITS_LIST,  sensorUnitsList);
            request.setAttribute(SENSORS_TYPES_LIST,  sensorTypesList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PageConstant.ADMIN_PAGE);
            requestDispatcher.forward(request, response);
            router.setPagePath(PageConstant.ADMIN_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
