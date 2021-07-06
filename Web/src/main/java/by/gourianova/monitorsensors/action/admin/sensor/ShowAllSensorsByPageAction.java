package by.gourianova.monitorsensors.action.admin.sensor;

import by.gourianova.monitorsensors.Sensor;
import by.gourianova.monitorsensors.User;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.SensorService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShowAllSensorsByPageAction implements Action {

    private final static int PAGE_CAPACITY = 4;
    private final static String PAGE = "page";
    private final static String LEFT_PAGE = "leftPage";
    private final static String RIGHT_PAGE = "rightPage";
    private final static String LEFT_PAGE_CLASS = "leftPageClass";
    private final static String RIGHT_PAGE_CLASS = "rightPageClass";
    private final static String GO_TO_LEFT_PAGE = "controller?action=show_sensors_page&page=";
    private final static String GO_TO_RIGHT_PAGE = "controller?action=show_sensors_page&page=";
    private final static String DISABLED_BUTTON = " disabled";
    private final static String NOT_ACTION = "";
    private final static String USER = "user";
    private final static String SENSORS_LIST = "sensorsList";
    private final static String MESSAGE = "message";
    private int pageNumber = 1;
    private SensorService sensorService = new SensorService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        String leftPage;
        String leftPageClass;
        String rightPage;
        String rightPageClass;
        ArrayList<Sensor> sensorsList;
        if (request.getParameter(PAGE) != null) {
            pageNumber = Integer.parseInt(request.getParameter(PAGE));
        }
        try {
            sensorsList = sensorService.findAllByPage(PAGE_CAPACITY, pageNumber);
            int sensorCount = sensorService.findAll().size();
            if (pageNumber > 1) {
                leftPage = GO_TO_LEFT_PAGE + (pageNumber - 1);
                leftPageClass = NOT_ACTION;
            } else {
                leftPage = NOT_ACTION;
                leftPageClass = DISABLED_BUTTON;
            }
            if (sensorCount >= pageNumber * PAGE_CAPACITY) {
                rightPage = GO_TO_RIGHT_PAGE + (pageNumber + 1);
                rightPageClass = NOT_ACTION;
            } else {
                rightPage = NOT_ACTION;
                rightPageClass = DISABLED_BUTTON;
            }
            User user = (User) request.getSession().getAttribute(USER);
            request.setAttribute(USER, user);
            request.setAttribute(SENSORS_LIST, sensorsList);
            request.setAttribute(LEFT_PAGE, leftPage);
            request.setAttribute(LEFT_PAGE_CLASS, leftPageClass);
            request.setAttribute(RIGHT_PAGE, rightPage);
            request.setAttribute(RIGHT_PAGE_CLASS, rightPageClass);
            router.setPagePath(PageConstant.MAIN_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
