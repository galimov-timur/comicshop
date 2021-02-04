package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;
import static kz.comicshop.service.constants.UserConstants.*;
import static kz.comicshop.service.constants.ServiceConstants.*;

import kz.comicshop.data.UserDAO;
import kz.comicshop.entity.User;
import kz.comicshop.util.*;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * LoginService - provides service for user authentication
 */
public class LoginService implements Service {
    static final Logger LOGGER = Logger.getLogger(LoginService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = LOGIN_PAGE;
        String action = request.getParameter(ACTION);
        String message;

        if(action != null) {

            if(action.equals(SIGN_IN)) {
                MessageManager messageManager = MessageManager.getInstance();
                String userEmail = request.getParameter(EMAIL);
                String passwordToCheck = request.getParameter(PASSWORD);
                User user = UserDAO.getUserByEmail(userEmail);

                if (user != null) {
                    if (isValidPassword(user, passwordToCheck)) {
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
                        message = messageManager.getWarningMessage("message.login.wrong.pass");
                        request.setAttribute(MESSAGE, message);
                    }
                } else {
                    message = messageManager.getWarningMessage("message.login.wrong.email");;
                    request.setAttribute(MESSAGE, message);
                }
            }
        }

        if(destPage.equals(INDEX_PAGE)) {
            destPage = HOME_SERVICE;
            response.sendRedirect(destPage.toLowerCase());
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        }
    }

    /**
     * Compares provided password and password stored in database
     * @param user - User
     * @param passwordToCheck - Password to check
     * @return - true if passwords match, and false otherwise
     */
    private boolean isValidPassword(User user, String passwordToCheck) {
        boolean isValidPassword = false;
        String userPassword = user.getPassword();
        String userSalt = user.getSalt();
        try {
            String hashedPasswordToCheck = PasswordUtil.hashPassword(passwordToCheck + userSalt);
            isValidPassword = hashedPasswordToCheck.equals(userPassword);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        return isValidPassword;
    }
}