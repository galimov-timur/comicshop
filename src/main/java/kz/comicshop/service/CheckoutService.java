package kz.comicshop.service;

import kz.comicshop.data.OrderDetailsDAO;
import kz.comicshop.data.OrderItemDAO;
import kz.comicshop.data.UserDAO;
import kz.comicshop.entity.Cart;
import kz.comicshop.entity.OrderDetails;
import kz.comicshop.entity.OrderItem;
import kz.comicshop.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CheckoutService implements Service {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ADDRESS_PAGE;
        String action = request.getParameter(ACTION);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Cart cart = (Cart)session.getAttribute(CART);

        if(cart != null && cart.getSize() > 0) {
            if(user == null) {
                String nextPage = ADDRESS_PAGE;
                String message = "<div class='message --warning'><p>Для продолжения требуется авторизация!</p></div>";
                session.setAttribute(NEXT_PAGE, nextPage);
                request.setAttribute(MESSAGE, message);
                destPage = LOGIN_PAGE;
            }

            if(action != null && action.equals(UPDATE)) {
                String address1 = request.getParameter("address1");
                String address2 = request.getParameter("address2");
                String city = request.getParameter("city");
                String zip = request.getParameter("zip");
                String country = request.getParameter("country");
                user.setAddress1(address1);
                user.setAddress2(address2);
                user.setCity(city);
                user.setZip(zip);
                user.setCountry(country);
                UserDAO.updateAddress(user);
                destPage = INVOICE_PAGE;
            }

            if(action != null && action.equals(ADD)) {
                ArrayList<OrderItem> orderItems = cart.getCartProducts();

                // total sum of products
                double totalAmount = cart.getTotal();

                // Date and time of order
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
                String currentDateTime = format.format(date);

                // Create new OrderDetails object to store data in database
                OrderDetails orderDetails = new OrderDetails(user, orderItems, currentDateTime, totalAmount, (short) 0);

                // returns id of this order in database
                long orderId = OrderDetailsDAO.insertOrder(orderDetails);

                // store in database products related to this order
                if(orderId != 0) {
                    OrderItemDAO.insertOrderItems(orderDetails, orderId);
                    cart.empty();
                }

                request.setAttribute(ORDER_ID, orderId);
                session.setAttribute(CART, cart);
                destPage = THANKS_PAGE;
            }
        } else {
            destPage = CART_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
