package by.gourianova.monitorsensors.action.admin.user;

import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.exception.ServiceException;
import by.gourianova.monitorsensors.service.UserService;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class DeleteUserAction implements Action {
    private final static String USER_ID = "userId";
    private final static String MESSAGE = "message";
    private final UserService userService = new UserService();
    private final static String ADMIN_PAGE="/controller?action=show_admin_page";
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        boolean isDeleted = false;
        try {
            isDeleted = userService.deleteEntityById(Integer.parseInt(request.getParameter(USER_ID)));
            if (isDeleted) {
                request.setAttribute(MESSAGE, "delete.user.ok");
                //  log.println("user with " + userId + " is deleted ");
            } else {
                request.setAttribute(MESSAGE, "delete.user.not");
                // log.println("user with " + userId + " is Not deleted ");
            }
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

