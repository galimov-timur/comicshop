package kz.comicshop.service;

import kz.comicshop.data.CategoryDAO;
import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Category;
import kz.comicshop.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String url = INDEX_PAGE;
        long defaultCategoryId = 1;

        Category category = CategoryDAO.getCategoryById(defaultCategoryId);
        ArrayList<Product> products = ProductDAO.getProductsByCategory(defaultCategoryId);

        if(category != null && products != null) {
            request.setAttribute(PRODUCTS, products);
            request.setAttribute(NAME, category.getName());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
