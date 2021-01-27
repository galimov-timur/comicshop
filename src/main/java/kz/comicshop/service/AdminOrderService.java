package kz.comicshop.service;

import kz.comicshop.data.OrderDetailsDAO;
import kz.comicshop.data.OrderItemDAO;
import kz.comicshop.entity.OrderDetails;
import kz.comicshop.entity.OrderItem;
import kz.comicshop.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminOrderService implements Service {

    static final Logger logger = Logger.getLogger(AdminOrderService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ConfigurationManager.getProperty("path.page.order");
        String orderDetailsIdString = request.getParameter(ID);
        String action = request.getParameter(ACTION);
        String message = "";

        if(action != null) {
            long orderDetailsIdAsLong = 0;
            try {
                orderDetailsIdAsLong = Long.parseLong(orderDetailsIdString);
            } catch(NumberFormatException e) {
                logger.error(e.getMessage());
            }

            if(action.equals(EDIT)) {
                OrderDetails orderDetails = OrderDetailsDAO.getOrderById(orderDetailsIdAsLong);
                ArrayList<OrderItem> orderItems = OrderItemDAO.getOrderItemsByOrderId(orderDetailsIdAsLong);

                if(orderDetails != null) {
                    orderDetails.setOrderItems(orderItems);
                }

                request.setAttribute(ORDER_DETAILS, orderDetails);
                destPage = ConfigurationManager.getProperty("path.page.manage_order");
            } else if (action.equals(UPDATE)) {
                String newStatus = request.getParameter(ORDER_STATUS);

                short newStatusAsShort = 0;
                try {
                    newStatusAsShort = Short.parseShort(newStatus);
                } catch(NumberFormatException e) {
                    logger.error(e.getMessage());
                }

                int result = OrderDetailsDAO.updateOrderStatus(orderDetailsIdAsLong, newStatusAsShort);
                if(result > 0) {
                    message = "<div class='message --success'><p>Статус заказа успешно обновлен</p></div>";
                } else {
                    message = "<div class='message --warning'><p>Не удалось обновить статус заказа</p></div>";
                }
            } else if (action.equals(REMOVE)) {
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
