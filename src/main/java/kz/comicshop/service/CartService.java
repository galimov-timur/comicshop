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

        String destPage = "/cart.jsp";

        String action = request.getParameter("action");
        String productId = request.getParameter("id");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if(action != null && action.equals("add")) {
            if(cart == null) {
                cart = new Cart();
            }

            long productIdAsLong = Long.parseLong(productId);
            Product productToAdd = ProductDAO.getProductById(productIdAsLong);

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productToAdd);
            orderItem.setQuantity(1);

            cart.addProduct(orderItem);
        }

        if(action != null && action.equals("remove")) {
            long productIdAsLong = Long.parseLong(productId);
            cart.removeProduct(productIdAsLong);
        }

        session.setAttribute("cart", cart);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
