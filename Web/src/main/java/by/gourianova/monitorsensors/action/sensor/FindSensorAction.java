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
    SensorService sensorService=new SensorService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();

        String params = request.getParameter(FIND_PARAMS);
        ArrayList<Sensor> foundedSensorsList=new ArrayList<>();
        Sensor sensor = new Sensor();
//TODO if is an appropriate util?
            try {
                String[] words = params.split(" ");
                for (String word : words) {
                 //TODO: write findSensorByName
                    // sensor = sensorService.findSensorByName(word);

                     if (sensor!=null)
                        foundedSensorsList.add(sensor);
                    //TODO: write findSensorByLocation
                     //else  {sensor = sensorService.findSensorByLocation(word);
                         if (sensor!=null)
                             foundedSensorsList.add(sensor);

                     }
                    for (Sensor sensor1 :foundedSensorsList) {
                        //If Id of sensor=Id of sensor1 sensor delete o not add
                    //} catch (ServiceException e) {
                }
                router.setPagePath(PageConstant.MAIN_PAGE);
            } catch (Exception  e) {
                request.getSession().setAttribute(MESSAGE, e.getMessage());
                router.setPagePath(PageConstant.ERROR_PAGE);
                router.setRoute(Router.RouteType.REDIRECT);
            }

        return router;   }
        }