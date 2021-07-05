package by.gourianova.monitorsensors.action.admin.user;

import by.gourianova.monitorsensors.*;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.*;
import by.gourianova.monitorsensors.util.PageConstant;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShowChangeRolePage implements Action {
    private final static String USERS_LIST = "usersList";
    private final static String ROLES_LIST = "rolesList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<User> usersList;
        ArrayList<Role> rolesList;
        try {
            usersList = userService.findAll();
            rolesList = roleService.findAll();
            request.setAttribute(USERS_LIST, usersList);
            request.setAttribute(ROLES_LIST, rolesList);
            router.setPagePath(PageConstant.UPDATE_USER);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}