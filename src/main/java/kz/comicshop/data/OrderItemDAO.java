package kz.comicshop.data;

import kz.comicshop.entity.OrderDetails;
import kz.comicshop.entity.OrderItem;
import kz.comicshop.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDAO {
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

    public static ArrayList<OrderItem> getOrderItemsByOrderId(long orderDetailsId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT order_item.quantity, products.product_id, products.product_name, products.product_price FROM order_item " +
                "INNER JOIN products ON order_item.product_id = products.product_id " +
                "WHERE order_item.order_details_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, orderDetailsId);
            rs = ps.executeQuery();
            OrderItem orderItem = null;
            Product product = null;
            ArrayList<OrderItem> orderItems = new ArrayList<>();
            while(rs.next()) {
                orderItem = new OrderItem();
                orderItem.setQuantity(rs.getInt("quantity"));
                product = new Product();
                product.setId(rs.getLong("product_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                orderItem.setProduct(product);
                orderItems.add(orderItem);
            }
            return orderItems;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
