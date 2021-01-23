package kz.comicshop.service;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Cart;
import kz.comicshop.entity.OrderItem;
import kz.comicshop.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CartService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = CART_PAGE;
        String action = request.getParameter(ACTION);

        if(action != null) {

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute(CART);
            String productId = request.getParameter(ID);
            long productIdAsLong = Long.parseLong(productId);

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
