package kz.comicshop.service;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowProductService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String destPage = "/index.jsp";

        String categoryId = request.getParameter("id");
        String categoryName = request.getParameter("name");

        if(categoryId != null && categoryName != null) {
            long id = Long.parseLong(categoryId);
            ArrayList<Product> products = ProductDAO.getProductsByCategory(id);
            request.setAttribute("products", products);
            request.setAttribute("categoryName", categoryName);
        }

        String productId = request.getParameter("productId");

        if(productId != null) {
            long productIdAsLong = Long.parseLong(productId);
            Product product = ProductDAO.getProductById(productIdAsLong);
            request.setAttribute("product", product);
            destPage = "/product.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
