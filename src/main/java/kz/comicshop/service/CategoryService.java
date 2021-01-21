package kz.comicshop.service;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String url = "/category.jsp";
        String categoryId = request.getParameter("id");
        String categoryName = request.getParameter("name");
        long id = Long.parseLong(categoryId);

        ArrayList<Product> products = ProductDAO.getProductsByCategory(id);
        System.out.println(products.size());

        request.setAttribute("products", products);
        request.setAttribute("categoryName", categoryName);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
