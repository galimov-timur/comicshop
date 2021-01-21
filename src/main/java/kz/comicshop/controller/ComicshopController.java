package kz.comicshop.controller;

import kz.comicshop.data.CategoryDAO;
import kz.comicshop.entity.Category;
import kz.comicshop.service.Service;
import kz.comicshop.service.factory.ServiceFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComicshopController extends HttpServlet {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI().toLowerCase();
        System.out.println(requestUri);

        ServletContext application = this.getServletContext();

        if(application.getAttribute("categories") == null) {
            ArrayList<Category> categories = CategoryDAO.getCategories();
            application.setAttribute("categories", categories);
        }

        Service currentService = serviceFactory.getService(requestUri);

        try {
            currentService.execute(req, resp);
        } catch (ParseException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}