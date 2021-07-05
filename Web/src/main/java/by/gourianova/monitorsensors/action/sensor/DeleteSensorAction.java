package by.gourianova.monitorsensors.action.sensor;

import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.SensorChange;
import by.gourianova.monitorsensors.User;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.UserService;
import by.gourianova.monitorsensors.service.SensorChangeService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteSensorAction implements Action {
    private final static String USER = "user";
    private final static String SENSOR_CHANGE = "sensorChange";
    private final static String MESSAGE = "message";
    private SensorChangeService orderService = new SensorChangeService();
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            User user = (User) request.getSession().getAttribute(USER);

                User updateUser = userService.findUserById(user.getId());
            SensorChange sensorChange = SensorChangeService.findEntityById(user.getId());
                request.getSession().setAttribute(SENSOR_CHANGE, sensorChange);
                request.getSession().setAttribute(USER, updateUser);
                router.setPagePath(PageConstant.DELETE_SENSOR);
                router.setRoute(Router.RouteType.REDIRECT);
            } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
