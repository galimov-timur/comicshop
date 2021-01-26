package kz.comicshop.service;

import kz.comicshop.data.OrderDetailsDAO;
import kz.comicshop.entity.OrderDetails;
import kz.comicshop.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
