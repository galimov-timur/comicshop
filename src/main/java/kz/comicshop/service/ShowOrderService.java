package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;

import kz.comicshop.data.OrderDetailsDAO;
import kz.comicshop.entity.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ShowOrderService - provides services for displaying user's orders
 */
public class ShowOrderService implements  Service{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ORDER_PAGE;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);

        if(user != null) {
            long userId = user.getId();
            ArrayList<OrderDetails> orders = OrderDetailsDAO.getOrdersByUserId(userId);
            request.setAttribute(ORDERS, orders);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
