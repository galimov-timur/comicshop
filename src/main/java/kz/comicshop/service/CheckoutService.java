package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;
import static kz.comicshop.service.constants.AddressConstants.*;

import kz.comicshop.data.*;
import kz.comicshop.entity.*;
import kz.comicshop.util.MessageManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * CheckoutService implements a range of methods to authenticate user, update delivery address,
 * store order information in database
 */
public class CheckoutService implements Service {
    private static final int EMPTY_CART = 0;
    private static final long ZERO_ID = 0;
    private static final short ORDER_STATUS_NEW = 0;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ADDRESS_PAGE;
        String action = request.getParameter(ACTION);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Cart cart = (Cart)session.getAttribute(CART);

        if(cart != null && cart.getSize() > EMPTY_CART) {
            if(user == null) {
                String nextPage = ADDRESS_PAGE;
                MessageManager messageManager = MessageManager.getInstance();
                String message = messageManager.getWarningMessage("message.checkout.login");
                session.setAttribute(NEXT_PAGE, nextPage);
                request.setAttribute(MESSAGE, message);
                destPage = LOGIN_PAGE;
            }

            if(action != null && action.equals(UPDATE)) {
                updateUserAddress(request, user);
                destPage = INVOICE_PAGE;
            }

            if(action != null && action.equals(ADD)) {
                ArrayList<OrderItem> orderItems = cart.getCartProducts();
                double totalAmount = cart.getTotal();
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN) ;
                String currentDateTime = format.format(date);
                OrderDetails orderDetails = new OrderDetails(user, orderItems, currentDateTime, totalAmount, ORDER_STATUS_NEW);
                long orderId = OrderDetailsDAO.insertOrder(orderDetails);

                if(orderId != ZERO_ID) {
                    OrderItemDAO.insertOrderItems(orderDetails, orderId);
                    cart.empty();
                }

                request.setAttribute(ORDER_ID, orderId);
                session.setAttribute(CART, cart);
                destPage = THANK_YOU_PAGE;
            }
        } else {
            destPage = CART_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

    private void updateUserAddress(HttpServletRequest request, User user) {
        String address1 = request.getParameter(ADDRESS1);
        String address2 = request.getParameter(ADDRESS2);
        String city = request.getParameter(CITY);
        String zip = request.getParameter(ZIP);
        String country = request.getParameter(COUNTRY);
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setCity(city);
        user.setZip(zip);
        user.setCountry(country);
        UserDAO.updateAddress(user);
    }
}
