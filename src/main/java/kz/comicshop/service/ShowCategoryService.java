package kz.comicshop.service;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowCategoryService implements Service {

    static final Logger logger = Logger.getLogger(ShowCategoryService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = INDEX_PAGE;

        String categoryId = request.getParameter(ID);
        String categoryName = request.getParameter(NAME);

        if(categoryId != null && categoryName != null) {
            long id = 0;
            try {
                id = Long.parseLong(categoryId);
            } catch(NumberFormatException e) {
                logger.error(e.getMessage());
            }

            ArrayList<Product> products = ProductDAO.getProductsByCategory(id);
            request.setAttribute(PRODUCTS, products);
            request.setAttribute(NAME, categoryName);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
