package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;
import kz.comicshop.util.MessageManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Provides error page if user request url is not found
 */
public class ErrorService implements Service {
    private static final int NOT_FOUND = 404;
    private static final String ERROR_CODE = "errorCode";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ERROR_PAGE;
        MessageManager messageManager = MessageManager.getInstance();
        String message = messageManager.getMessage("message.page.not.found");

        response.setStatus(NOT_FOUND);
        request.setAttribute(MESSAGE, message);
        request.setAttribute(ERROR_CODE, NOT_FOUND);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
