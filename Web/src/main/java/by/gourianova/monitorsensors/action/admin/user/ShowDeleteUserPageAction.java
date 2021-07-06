package by.gourianova.monitorsensors.action.admin.user;

import by.gourianova.monitorsensors.action.Action;
import by.gourianova.monitorsensors.controller.Router;
import by.gourianova.monitorsensors.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.core.impl.ThrowableFormatOptions.MESSAGE;

public class ShowDeleteUserPageAction implements Action {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {

            router.setPagePath(PageConstant.DELETE_USER_PAGE);
            //    log.println("GoToDeleteUserPage OK ");
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (Exception e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
