package kz.comicshop.service;

import kz.comicshop.util.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ErrorService implements Service {

    public static final int NOT_FOUND = 404;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ConfigurationManager.getProperty("path.page.error");
        String message = "Страница не найдена";

        response.setStatus(NOT_FOUND);

        request.setAttribute(MESSAGE, message);
        request.setAttribute(ERROR_CODE, NOT_FOUND);

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
