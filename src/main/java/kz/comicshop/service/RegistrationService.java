package kz.comicshop.service;

import kz.comicshop.data.UserDAO;
import kz.comicshop.entity.User;
import kz.comicshop.util.PasswordUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegistrationService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = "/registration.jsp";
        String action = request.getParameter("action");

        if(action == null) {
            destPage = "/registration.jsp";
        } else if(action.equals("registerUser")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String passwordRepeat = request.getParameter("passwordRepeat");

            User user = new User();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhone(phone);

            if(password.equals(passwordRepeat)) {
                String salt = PasswordUtil.saltPassword(password);
                user.setSalt(salt);
                try {
                    String hashedPassword = PasswordUtil.hashPassword(password + salt);
                    user.setPassword(hashedPassword);
                } catch(NoSuchAlgorithmException e) {
                    System.out.println(e.getMessage());
                }
            }

            long userId = UserDAO.insertUser(user);
            user.setId(userId);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            destPage = "/index.jsp";
        }

        if(destPage.equals("/index.jsp")) {
            response.sendRedirect("/comicshop");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        }
    }
}
