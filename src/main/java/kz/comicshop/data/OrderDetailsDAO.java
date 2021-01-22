package kz.comicshop.data;

import kz.comicshop.entity.OrderDetails;
import kz.comicshop.entity.OrderItem;
import kz.comicshop.entity.Product;
import kz.comicshop.entity.User;

import java.sql.*;
import java.util.ArrayList;

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

    public static ArrayList<OrderDetails> getOrdersByUserId(long userId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM order_details WHERE user_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, userId);
            rs = ps.executeQuery();
            OrderDetails orderDetails = null;
            ArrayList<OrderDetails> orders = new ArrayList<>();
            while(rs.next()) {
                orderDetails = new OrderDetails();
                orderDetails.setOrderId(rs.getLong("order_details_id"));
                orderDetails.setOrderDate(rs.getString("order_date"));
                orderDetails.setTotalAmount(rs.getDouble("total_amount"));
                orderDetails.setOrderStatus(rs.getShort("order_status"));
                orders.add(orderDetails);
            }
            return orders;
        } catch(SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<OrderDetails> getOrderDetails() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;

        String query = "SELECT * FROM order_details;";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            OrderDetails orderDetails = null;
            ArrayList<OrderDetails> orders = new ArrayList<>();
            while (rs.next()) {
                orderDetails = new OrderDetails();
                orderDetails.setOrderId(rs.getLong("order_details_id"));
                orderDetails.setOrderDate(rs.getString("order_date"));
                orderDetails.setTotalAmount(rs.getDouble("total_amount"));
                orderDetails.setOrderStatus(rs.getShort("order_status"));
                orders.add(orderDetails);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closeStatement(statement);
            pool.freeConnection(connection);
        }
    }

    public static OrderDetails getOrderById(long orderDetailsId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM order_details WHERE order_details_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, orderDetailsId);
            rs = ps.executeQuery();
            OrderDetails orderDetails = null;
            User user = null;
            while(rs.next()) {
                orderDetails = new OrderDetails();
                orderDetails.setOrderId(rs.getLong("order_details_id"));
                orderDetails.setOrderDate(rs.getString("order_date"));
                orderDetails.setTotalAmount(rs.getDouble("total_amount"));
                orderDetails.setOrderStatus(rs.getShort("order_status"));
                user = UserDAO.getUserById(rs.getLong("user_id"));
                orderDetails.setUser(user);
            }
            return orderDetails;
        } catch(SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int updateOrderStatus(long orderDetailsId, short status) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE order_details SET order_status=? WHERE order_details_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setShort(1, status);
            ps.setLong(2, orderDetailsId);
            return ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int deleteById(long orderDetailsId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM order_details WHERE order_details_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, orderDetailsId);
            return ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
