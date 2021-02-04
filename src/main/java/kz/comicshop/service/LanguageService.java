package kz.comicshop.service;

import static kz.comicshop.service.constants.ServiceConstants.*;
import static kz.comicshop.service.constants.CommonConstants.*;
import kz.comicshop.util.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

/**
 * LanguageService - changes application locale preferences
 */
public class LanguageService implements Service {
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = HOME_SERVICE;
        String lang = request.getParameter(LANG);
        Locale newLocale = new Locale(lang);
        MessageManager messageManager = MessageManager.getInstance();
        messageManager.changeResource(newLocale);
        HttpSession session = request.getSession();
        session.setAttribute(LANG, lang);
        response.sendRedirect(destPage.toLowerCase());
    }
}
