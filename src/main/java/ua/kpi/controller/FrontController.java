package ua.kpi.controller;

import org.apache.log4j.Logger;
import ua.kpi.controller.command.Command;
import ua.kpi.controller.validator.RegExp;
import ua.kpi.utils.AttributesHolder;
import ua.kpi.utils.ErrorsMessages;
import ua.kpi.utils.PagesHolder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(FrontController.class);
    private CommandHolder commandHolder;

    @Override
    public void init() { commandHolder = new CommandHolder(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandKey = request.getMethod().toUpperCase() + CommandHolder.DELIMITER + getPath(request);
        Command command = commandHolder.getCommand(commandKey);
        try {
            String path = command.execute(request, response);
            if (path.startsWith("redirect:")) {
                response.sendRedirect(path.substring(9));
            } else {
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (Exception e) {
            logger.error(e);
            request.setAttribute(AttributesHolder.ERROR_MESSAGE, ErrorsMessages.NOT_EXCEPTED_ERROR);
            request.getRequestDispatcher(PagesHolder.PAGE_NOT_FOUND).forward(request, response);
        }
    }

    private String getPath(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        return path.replaceAll(RegExp.NUMBER, "");
    }
}