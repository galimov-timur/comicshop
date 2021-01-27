package kz.comicshop.service;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Product;
import kz.comicshop.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowProductService implements Service {

    static final Logger logger = Logger.getLogger(ShowProductService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ConfigurationManager.getProperty("path.page.index");
        String productId = request.getParameter(ID);

        if(productId != null) {
            long productIdAsLong = 0;
            try {
                productIdAsLong = Long.parseLong(productId);
            } catch(NumberFormatException e) {
                logger.error(e.getMessage());
            }
            Product product = ProductDAO.getProductById(productIdAsLong);
            request.setAttribute(PRODUCT, product);
            destPage = ConfigurationManager.getProperty("path.page.product");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
