package kz.comicshop.controller;

import kz.comicshop.data.CategoryDAO;
import kz.comicshop.entity.Category;
import kz.comicshop.service.Service;
import kz.comicshop.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComicshopController extends HttpServlet {

    static final Logger logger = Logger.getLogger(ComicshopController.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath("/");
        String fileName = getInitParameter("logConfigFile");
        if(fileName != null) {
            PropertyConfigurator.configure(prefix + fileName);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI().toLowerCase();
        ServletContext application = this.getServletContext();

        if(application.getAttribute(Service.CATEGORIES) == null) {
            ArrayList<Category> categories = CategoryDAO.getCategories();
            application.setAttribute(Service.CATEGORIES, categories);
        }

        Service currentService = serviceFactory.getService(requestUri);

        try {
            currentService.execute(req, resp);
        } catch (ParseException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}