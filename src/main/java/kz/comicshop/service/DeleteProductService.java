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

public class DeleteProductService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String destPage = CATALOG_PAGE;
        String action = request.getParameter(ACTION);

        if(action != null) {
            if(action.equals(SHOW)) {
                String productCategoryId = request.getParameter(CATEGORY_ID);
                long categoryId = Long.parseLong(productCategoryId);
                ArrayList<Product> products =  ProductDAO.getProductsByCategory(categoryId);
                request.setAttribute(PRODUCTS, products);
            } else if(action.equals(REMOVE)) {
                String productId = request.getParameter(ID);
                long productIdAsLong = Long.parseLong(productId);
                int result = ProductDAO.delete(productIdAsLong);
                String message;
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
