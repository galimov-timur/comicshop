package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;
import static kz.comicshop.service.constants.ProductConstants.*;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ShowCategoryService selects products by category, and displays them on the index page
 */
public class ShowCategoryService implements Service {
    static final Logger LOGGER = Logger.getLogger(ShowCategoryService.class);

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
                LOGGER.error(e.getMessage());
            }
            ArrayList<Product> products = ProductDAO.getProductsByCategory(id);
            request.setAttribute(PRODUCTS, products);
            request.setAttribute(NAME, categoryName);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
