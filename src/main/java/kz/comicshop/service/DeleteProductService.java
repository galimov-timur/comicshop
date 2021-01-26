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

public class DeleteProductService implements Service {

    static final Logger logger = Logger.getLogger(DeleteProductService.class);

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
                    logger.error(e.getMessage());
                }

                ArrayList<Product> products =  ProductDAO.getProductsByCategory(categoryId);
                request.setAttribute(PRODUCTS, products);
            } else if(action.equals(REMOVE)) {
                String message;
                String productId = request.getParameter(ID);

                long productIdAsLong = 0;
                try {
                    productIdAsLong = Long.parseLong(productId);
                } catch(NumberFormatException e) {
                    logger.error(e.getMessage());
                }

                int result = ProductDAO.delete(productIdAsLong);
                if(result > 0) {
                    message = "<div class='message --success'><p>Товар успешно удален</p></div>";
                } else {
                    message = "<div class='message --warning'><p>Не удалось удалить товар</p></div>";
                }

                request.setAttribute(MESSAGE, message);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
