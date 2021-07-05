package by.gourianova.monitorsensors.action;

import by.gourianova.monitorsensors.controller.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
