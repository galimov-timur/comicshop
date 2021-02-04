package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;
import static kz.comicshop.service.constants.UserConstants.*;
import static kz.comicshop.service.constants.ServiceConstants.*;

import kz.comicshop.data.UserDAO;
import kz.comicshop.entity.*;
import kz.comicshop.util.PasswordUtil;
import kz.comicshop.validation.FormValidator;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * RegistrationService implements methods to register new user in application. Before adding user
 * information to the database, all parameters validated by FormValidator class from util package.
 */
public class RegistrationService implements Service {
    static final Logger LOGGER = Logger.getLogger(RegistrationService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = REGISTRATION_PAGE;
        String action = request.getParameter(ACTION);

        if(action != null) {

            if(action.equals(ADD)) {
                FormValidator formValidation = new FormValidator();
                User user = validateUserParameters(request, formValidation);

                if(user != null) {
                    String salt = PasswordUtil.saltPassword();
                    String password = user.getPassword();
                    user.setSalt(salt);

                    try {
                        String hashedPassword = PasswordUtil.hashPassword(password + salt);
                        user.setPassword(hashedPassword);
                    } catch (NoSuchAlgorithmException e) {
                        LOGGER.error(e.getMessage());
                    }

                    long userId = UserDAO.insertUser(user);
                    user.setId(userId);
                    HttpSession session = request.getSession();
                    session.setAttribute(USER, user);
                    destPage = INDEX_PAGE;

                } else {
                    request.setAttribute(FORM, formValidation);
                    destPage = REGISTRATION_PAGE;
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
     * Validates input from the user registration form
     * @param request - Request
     * @param validator - Form validator object
     * @return - User object if data is valid
     */
    private User validateUserParameters(HttpServletRequest request, FormValidator validator) {
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        String phone = request.getParameter(PHONE);
        String password = request.getParameter(PASSWORD);
        String passwordRepeat = request.getParameter(PASSWORD_REPEAT);

        validator.setFirstName(firstName);
        validator.setLastName(lastName);
        validator.setEmail(email);
        validator.setPhone(phone);
        validator.setPassword(password);
        validator.setPasswordRepeat(passwordRepeat);

        if(validator.process()) {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhone(phone);
            return user;
        }
        return null;
    }
}
