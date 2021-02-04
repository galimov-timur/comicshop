package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;

import kz.comicshop.data.*;
import kz.comicshop.entity.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * HomeService displays products of default category (which is one), on the index page
 */
public class HomeService implements Service {
    private static final String PRODUCTS = "products";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = INDEX_PAGE;
        long defaultCategoryId = 1;

        Category category = CategoryDAO.getCategoryById(defaultCategoryId);
        ArrayList<Product> products = ProductDAO.getProductsByCategory(defaultCategoryId);

        if(category != null && products != null) {
            request.setAttribute(PRODUCTS, products);
            request.setAttribute(NAME, category.getName());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
