package kz.comicshop.service;

import kz.comicshop.data.UserDAO;
import kz.comicshop.entity.User;
import kz.comicshop.util.ConfigurationManager;
import kz.comicshop.util.PasswordUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class LoginService implements Service {

    static final Logger logger = Logger.getLogger(LoginService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ConfigurationManager.getProperty("path.page.login");
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
                        logger.error(e.getMessage());
                    }

                    if (isRegisteredUser) {
                        HttpSession session = request.getSession();
                        session.setAttribute(USER, user);
                        String nextPage = (String) session.getAttribute(NEXT_PAGE);
                        if (nextPage != null) {
                            destPage = nextPage;
                            session.removeAttribute(NEXT_PAGE);
                        } else {
                            destPage = ConfigurationManager.getProperty("path.page.index");
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

        if(destPage.equals(ConfigurationManager.getProperty("path.page.index"))) {
            response.sendRedirect("/comicshop");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        }
    }
}