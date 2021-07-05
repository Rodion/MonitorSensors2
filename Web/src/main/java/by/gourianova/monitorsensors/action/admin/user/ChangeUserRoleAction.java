package by.gourianova.monitorsensors.action.admin.user;

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


public class ChangeUserRoleAction implements Action {
    private final static String USER_ID = "userId";
    private final static String ROLE_ID = "roleId";
    private final static String MESSAGE = "message";
    private final static String ADMIN_PAGE="/controller?action=show_admin_page";
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        User user=new User();
        try {
            user=userService.findUserById(Integer.parseInt(request.getParameter(USER_ID)));
             user.setRoleId(Integer.parseInt(request.getParameter(ROLE_ID)));

            userService.updateUser(user);
            router.setPagePath(ADMIN_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
