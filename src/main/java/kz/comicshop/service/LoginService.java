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

public class LoginService implements Service {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = "/login.jsp";
        String action = request.getParameter("action");
        String message = "";

        if(action == null) {
            destPage = "/login.jsp";
        } else if(action.equals("login")) {

            String userEmail = request.getParameter("email");
            String passwordToCheck = request.getParameter("password");
            User user = UserDAO.getUserByEmail(userEmail);
            boolean isRegisteredUser = false;

            if(user != null) {
                String userPassword = user.getPassword();
                String userSalt = user.getSalt();

                try {
                    String hashedPasswordToCheck = PasswordUtil.hashPassword(passwordToCheck + userSalt);
                    isRegisteredUser = hashedPasswordToCheck.equals(userPassword);
                } catch (NoSuchAlgorithmException e) {
                    System.out.println(e);
                }

                if(isRegisteredUser) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    String nextPage = (String) session.getAttribute("nextPage");
                    if(nextPage != null) {
                        destPage = nextPage;
                        session.removeAttribute("nextPage");
                    } else {
                        destPage = "/index.jsp";
                    }
                } else {
                    message = "<div class='message --warning'><p>Введен неверный пароль</p></div>";
                    request.setAttribute("message", message);
                }
            } else {
                message = "<div class='message --warning'><p>Пользователь с таким email адресом не зарегистрирован</p></div>";
                request.setAttribute("message", message);
            }
        }

        if(destPage.equals("/index.jsp")) {
            response.sendRedirect("/comicshop");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        }
    }
}