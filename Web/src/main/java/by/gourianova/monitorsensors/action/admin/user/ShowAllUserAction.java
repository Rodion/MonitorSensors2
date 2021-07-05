package by.gourianova.monitorsensors.action.admin.user;

import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.User;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.UserService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShowAllUserAction implements Action {
    private final static String USERS_LIST = "usersList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<User> usersList;
        try {
            usersList = userService.findAll();
            request.setAttribute(USERS_LIST, usersList);
            router.setPagePath(PageConstant.ALL_USERS);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
