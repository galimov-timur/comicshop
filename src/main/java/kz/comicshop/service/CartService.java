package kz.comicshop.service;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Cart;
import kz.comicshop.entity.OrderItem;
import kz.comicshop.entity.Product;
import kz.comicshop.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CartService implements Service {

    static final Logger logger = Logger.getLogger(CartService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ConfigurationManager.getProperty("path.page.cart");
        String action = request.getParameter(ACTION);

        if(action != null) {

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute(CART);
            String productId = request.getParameter(ID);

            long productIdAsLong = 0;
            try {
                productIdAsLong = Long.parseLong(productId);
            } catch(NumberFormatException e) {
                logger.error(e.getMessage());
            }

            if(action.equals(ADD)) {

                if(cart == null) {
                    cart = new Cart();
                }

                Product productToAdd = ProductDAO.getProductById(productIdAsLong);

                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(productToAdd);
                orderItem.setQuantity(1);
                cart.addProduct(orderItem);

            } else if(action.equals(REMOVE)) {
                if(cart != null) {
                    cart.removeProduct(productIdAsLong);
                }
            }

            session.setAttribute(CART, cart);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
