package kz.comicshop.data;

import kz.comicshop.entity.OrderDetails;
import kz.comicshop.entity.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsDAO {
    public static long insertOrder(OrderDetails order) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO order_details (user_id, order_date, total_amount, order_status) VALUES (?, ?, ?, ?) RETURNING order_details_id";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, order.getUser().getId());
            ps.setString(2, order.getOrderDate());
            ps.setDouble(3, order.getTotalAmount());
            ps.setShort(4, order.getOrderStatus());
            rs = ps.executeQuery();
            long orderDetailsId = 0;
            if(rs.next()) {
                orderDetailsId = rs.getLong("order_details_id");
            }
            return orderDetailsId;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int insertOrderItems(OrderDetails order, long orderId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO order_item (order_details_id, product_id, quantity) VALUES (?, ?, ?)";
        try {
            for(OrderItem item: order.getOrderItems()) {
                ps = connection.prepareStatement(query);
                ps.setLong(1, orderId);
                ps.setLong(2, item.getProduct().getId());
                ps.setDouble(3, item.getQuantity());
                ps.executeUpdate();
            }
            return 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
