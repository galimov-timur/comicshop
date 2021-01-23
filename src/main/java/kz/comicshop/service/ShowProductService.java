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

        String destPage = INDEX_PAGE;

        String productId = request.getParameter(ID);

        if(productId != null) {
            long productIdAsLong = Long.parseLong(productId);
            Product product = ProductDAO.getProductById(productIdAsLong);
            request.setAttribute(PRODUCT, product);
            destPage = PRODUCT_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
