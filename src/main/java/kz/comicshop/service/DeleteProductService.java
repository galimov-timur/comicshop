package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;
import static kz.comicshop.service.constants.ProductConstants.*;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Product;
import kz.comicshop.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DeleteProductService - provides services to show all products of a particular category, and remove product
 */
public class DeleteProductService implements Service {
    static final Logger LOGGER = Logger.getLogger(DeleteProductService.class);
    private static final String CATEGORY_ID = "categoryId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = CATALOG_PAGE;
        String action = request.getParameter(ACTION);

        if(action != null) {

            if(action.equals(SHOW)) {
                String productCategoryId = request.getParameter(CATEGORY_ID);
                long categoryId = 0;

                try {
                    categoryId = Long.parseLong(productCategoryId);
                } catch(NumberFormatException e) {
                    LOGGER.error(e.getMessage());
                }
                ArrayList<Product> products =  ProductDAO.getProductsByCategory(categoryId);
                request.setAttribute(PRODUCTS, products);

            } else if(action.equals(REMOVE)) {
                String message = removeProduct(request);
                request.setAttribute(MESSAGE, message);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

    /**
     * Removes product from database
     * @param request - HttpServletRequest
     * @return - String, result message
     */
    private String removeProduct(HttpServletRequest request) {
        String message;
        String productId = request.getParameter(ID);
        long productIdAsLong = 0;

        try {
            productIdAsLong = Long.parseLong(productId);
        } catch(NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        int result = ProductDAO.delete(productIdAsLong);
        MessageManager messageManager = MessageManager.getInstance();

        if(result > 0) {
            message = messageManager.getSuccessMessage("message.product.remove.success");
        } else {
            message = messageManager.getWarningMessage("message.product.remove.failure");
        }
        return message;
    }
}
