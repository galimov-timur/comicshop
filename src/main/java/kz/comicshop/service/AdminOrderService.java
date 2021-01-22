package kz.comicshop.service;

import kz.comicshop.data.OrderDetailsDAO;
import kz.comicshop.data.OrderItemDAO;
import kz.comicshop.entity.OrderDetails;
import kz.comicshop.entity.OrderItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminOrderService implements Service {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ORDER_PAGE;
        String message = "";
        String orderDetailsIdString = request.getParameter(ID);
        String action = request.getParameter(ACTION);

        if(action != null) {
            long orderDetailsIdAsLong = Long.parseLong(orderDetailsIdString);

            if(action.equals("edit")) {
                OrderDetails orderDetails = OrderDetailsDAO.getOrderById(orderDetailsIdAsLong);
                ArrayList<OrderItem> orderItems = OrderItemDAO.getOrderItemsByOrderId(orderDetailsIdAsLong);
                orderDetails.setOrderItems(orderItems);

                request.setAttribute(ORDER_DETAILS, orderDetails);
                destPage = MANAGE_ORDER_PAGE;
            } else if (action.equals("updateStatus")) {
                String newStatus = request.getParameter("orderStatus");
                short newStatusAsShort = Short.parseShort(newStatus);

                int result = OrderDetailsDAO.updateOrderStatus(orderDetailsIdAsLong, newStatusAsShort);
                if(result > 0) {
                    message = "<div class='message --success'><p>Статус заказа успешно обновлен</p></div>";
                } else {
                    message = "<div class='message --warning'><p>Не удалось обновить статус заказа</p></div>";
                }
            } else if (action.equals("deleteOrder")) {
                int result = OrderDetailsDAO.deleteById(orderDetailsIdAsLong);
                if(result > 0) {
                    message = "<div class='message --success'><p>Заказ успешно удален</p></div>";
                } else {
                    message = "<div class='message --warning'><p>Не удалось удалить заказ</p></div>";
                }
            }
        }

        ArrayList<OrderDetails> orderDetails = OrderDetailsDAO.getOrderDetails();
        request.setAttribute(ORDERS, orderDetails);
        request.setAttribute(MESSAGE, message);

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
