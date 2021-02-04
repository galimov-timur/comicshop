package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;

import kz.comicshop.data.*;
import kz.comicshop.entity.*;
import kz.comicshop.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * AdminOrderService implements methods to manage user orders
 */
public class AdminOrderService implements Service {
    private static final Logger LOGGER = Logger.getLogger(AdminOrderService.class);
    private static final MessageManager MESSAGE_MANAGER = MessageManager.getInstance();
    private static final String ORDER_DETAILS = "orderDetails";
    private static final String ORDER_STATUS = "orderStatus";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ORDER_PAGE;
        String orderDetailsIdString = request.getParameter(ID);
        String action = request.getParameter(ACTION);
        String message = EMPTY_STRING;

        if(action != null) {
            long orderDetailsIdAsLong = 0;
            try {
                orderDetailsIdAsLong = Long.parseLong(orderDetailsIdString);
            } catch(NumberFormatException e) {
                LOGGER.error(e.getMessage());
            }
            if(action.equals(EDIT)) {
                OrderDetails orderDetails = OrderDetailsDAO.getOrderById(orderDetailsIdAsLong);
                ArrayList<OrderItem> orderItems = OrderItemDAO.getOrderItemsByOrderId(orderDetailsIdAsLong);
                if(orderDetails != null) {
                    orderDetails.setOrderItems(orderItems);
                }
                request.setAttribute(ORDER_DETAILS, orderDetails);
                destPage = MANAGE_ORDER_PAGE;
            } else if (action.equals(UPDATE)) {
                message = updateOrderStatus(request, orderDetailsIdAsLong);
            } else if (action.equals(REMOVE)) {
                message = removeOrder(request, orderDetailsIdAsLong);
            }
        }

        ArrayList<OrderDetails> orderDetails = OrderDetailsDAO.getOrderDetails();
        request.setAttribute(ORDERS, orderDetails);
        request.setAttribute(MESSAGE, message);

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

    /**
     * Updates order status in database by it's id, 0 - new, 1 - processing, 2 - delivered
     * @param request - HttpServletRequest
     * @param orderDetailsId - long orderId
     * @return String, result message
     */
    private String updateOrderStatus(HttpServletRequest request, long orderDetailsId) {
        String message;
        short newStatusAsShort = 0;
        String newStatus = request.getParameter(ORDER_STATUS);
        try {
            newStatusAsShort = Short.parseShort(newStatus);
        } catch(NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        int result = OrderDetailsDAO.updateOrderStatus(orderDetailsId, newStatusAsShort);
        if(result > 0) {
            message = MESSAGE_MANAGER.getSuccessMessage("message.order.update.success");
        } else {
            message = MESSAGE_MANAGER.getWarningMessage("message.order.update.failure");
        }
        return message;
    }

    /**
     * Removes order from database by it's id
     * @param request - HttpServletRequest
     * @param orderDetailsId - long orderId
     * @return String, result message
     */
    private String removeOrder(HttpServletRequest request, long orderDetailsId) {
        String message;
        int result = OrderDetailsDAO.deleteById(orderDetailsId);
        if(result > 0) {
            message = MESSAGE_MANAGER.getSuccessMessage("message.order.remove.success");
        } else {
            message = MESSAGE_MANAGER.getWarningMessage("message.order.remove.failure");
        }
        return message;
    }
}
