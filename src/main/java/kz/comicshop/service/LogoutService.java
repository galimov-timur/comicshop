package kz.comicshop.service;

import static kz.comicshop.service.constants.ServiceConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class LogoutService implements Service {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = HOME_SERVICE;
        HttpSession session = request.getSession();

        if(session != null) {
            session.invalidate();
        }
        response.sendRedirect(destPage.toLowerCase());
    }
}
