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

/**
 * ShowProductService - gets product from the database, and displays it on the product page
 */
public class ShowProductService implements Service {
    static final Logger LOGGER = Logger.getLogger(ShowProductService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = INDEX_PAGE;
        String productId = request.getParameter(ID);

        if(productId != null) {
            long productIdAsLong = 0;
            try {
                productIdAsLong = Long.parseLong(productId);
            } catch(NumberFormatException e) {
                LOGGER.error(e.getMessage());
            }

            Product product = ProductDAO.getProductById(productIdAsLong);
            request.setAttribute(PRODUCT, product);
            destPage = PRODUCT_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
