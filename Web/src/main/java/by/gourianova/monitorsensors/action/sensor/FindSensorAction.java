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
import java.util.ArrayList;

public class FindSensorAction implements Action {
    private final static String FIND_PARAMS = "findString";
    private final static String MESSAGE = "message";
    private final static String SENSORS_LIST = "sensorsList";
    private final SensorService sensorService = new SensorService();


    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();

        String params = request.getParameter(FIND_PARAMS);
        ArrayList<Sensor> sensorsList = new ArrayList<>();
        ArrayList<Sensor> foundedSensorsList = new ArrayList<>();

        Sensor sensor = new Sensor();
//TODO if is an appropriate util?


        String[] words = params.split(" ");
        try {
            sensorsList = sensorService.findAll();

            for (Sensor sesensor1 : sensorsList) {
                for (String word : words) {
                    if (sesensor1.getName().contains(word) ||
                            sesensor1.getModel().contains(word)) {
                        //TODO: add? type & unit
                        foundedSensorsList.add(sesensor1);
                    } else if (sesensor1.getDescription() != null)
                        if (sesensor1.getDescription().contains(word)) {
                            foundedSensorsList.add(sesensor1);
                        }
                }
                request.setAttribute(SENSORS_LIST, foundedSensorsList);
                router.setPagePath(PageConstant.SENSOR_FIND);
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);

        }

        return router;
    }
}