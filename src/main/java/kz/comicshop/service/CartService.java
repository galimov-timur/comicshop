package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * CartService creates cart object and stores it in session, allowing user to add and remove products from it
 */
public class CartService implements Service {
    static final Logger LOGGER = Logger.getLogger(CartService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = CART_PAGE;
        String action = request.getParameter(ACTION);

        if(action != null) {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute(CART);
            String productId = request.getParameter(ID);

            long productIdAsLong = 0;
            try {
                productIdAsLong = Long.parseLong(productId);
            } catch(NumberFormatException e) {
                LOGGER.error(e.getMessage());
            }

            if(action.equals(ADD)) {
                if(cart == null) {
                    cart = new Cart();
                }
                Product productToAdd = ProductDAO.getProductById(productIdAsLong);
                int productQuantity = 1;
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(productToAdd);
                orderItem.setQuantity(productQuantity);
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
