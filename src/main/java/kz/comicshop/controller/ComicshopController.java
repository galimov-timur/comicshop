package kz.comicshop.controller;

import static kz.comicshop.service.constants.CommonConstants.*;

import kz.comicshop.data.CategoryDAO;
import kz.comicshop.entity.Category;
import kz.comicshop.service.Service;
import kz.comicshop.service.factory.ServiceFactory;
import kz.comicshop.util.*;
import org.apache.log4j.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Comicshop Controller handles user requests by getting instance of Service Factory class
 * and executing appropriate service. It also initializes logger configuration file,
 * sets categories in application context and default language in Http session.
 */
public class ComicshopController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(ComicshopController.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final String ROOT_DIRECTORY = "/";

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath(ROOT_DIRECTORY);
        String fileName = getInitParameter("logConfigFile");
        if(fileName != null) {
            PropertyConfigurator.configure(prefix + fileName);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI().toLowerCase();
        ServletContext application = this.getServletContext();
        if(application.getAttribute(CATEGORIES) == null) {
            ArrayList<Category> categories = CategoryDAO.getCategories();
            application.setAttribute(CATEGORIES, categories);
        }

        HttpSession session = req.getSession();
        String lang = (String) session.getAttribute(LANG);
        if(lang==null){
            session.setAttribute(LANG, LANG_RU);
            MessageManager messageManager = MessageManager.getInstance();
            Locale locale = new Locale(LANG_RU);
            messageManager.changeResource(locale);
        }

        Service currentService = serviceFactory.getService(requestUri);
        try {
            currentService.execute(req, resp);
        } catch (ParseException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}