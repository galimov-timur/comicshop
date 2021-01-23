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

        String destPage = LOGIN_PAGE;
        String action = request.getParameter(ACTION);
        String message = "";

        if(action != null) {

            if(action.equals(SIGN_IN)) {

                String userEmail = request.getParameter(EMAIL);
                String passwordToCheck = request.getParameter(PASSWORD);
                User user = UserDAO.getUserByEmail(userEmail);
                boolean isRegisteredUser = false;

                if (user != null) {
                    String userPassword = user.getPassword();
                    String userSalt = user.getSalt();

                    try {
                        String hashedPasswordToCheck = PasswordUtil.hashPassword(passwordToCheck + userSalt);
                        isRegisteredUser = hashedPasswordToCheck.equals(userPassword);
                    } catch (NoSuchAlgorithmException e) {
                        System.out.println(e);
                    }

                    if (isRegisteredUser) {
                        HttpSession session = request.getSession();
                        session.setAttribute(USER, user);
                        String nextPage = (String) session.getAttribute(NEXT_PAGE);
                        if (nextPage != null) {
                            destPage = nextPage;
                            session.removeAttribute(NEXT_PAGE);
                        } else {
                            destPage = INDEX_PAGE;
                        }
                    } else {
                        message = "<div class='message --warning'><p>Введен неверный пароль</p></div>";
                        request.setAttribute(MESSAGE, message);
                    }
                } else {
                    message = "<div class='message --warning'><p>Пользователь с таким email адресом не зарегистрирован</p></div>";
                    request.setAttribute(MESSAGE, message);
                }
            }
        }

        if(destPage.equals(INDEX_PAGE)) {
            response.sendRedirect("/comicshop");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        }
    }
}