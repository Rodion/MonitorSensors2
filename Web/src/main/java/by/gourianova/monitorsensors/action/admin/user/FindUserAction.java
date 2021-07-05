package by.gourianova.monitorsensors.action.admin.user;

import by.gourianova.monitorsensors.Role;
import by.gourianova.monitorsensors.User;
import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.RoleService;
import by.gourianova.monitorsensors.service.UserService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
public class FindUserAction implements Action {
    private final static String USER_ID = "userId";
    private final static String USER = "userOne";
    private final static String EMPTY_USER = "emptyUser";
    private final static String ROLES_LIST = "rolesList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            int userId = Integer.parseInt(request.getParameter(USER_ID));
            User user = userService.findUserById(userId);
            if (user != null) {
                request.setAttribute(USER, user);
                ArrayList<Role> rolesList;
                rolesList = roleService.findAll();
                request.setAttribute(ROLES_LIST, rolesList);

            } else {
                request.setAttribute(EMPTY_USER, Boolean.TRUE);
            }

            router.setPagePath(PageConstant.ONE_USER);
            //  log.println(router.getPagePath());
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
