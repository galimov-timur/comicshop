package kz.comicshop.data;

import kz.comicshop.entity.OrderDetails;
import kz.comicshop.entity.User;
import kz.comicshop.util.DbUtility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * OrderDetailsDAO class contains basic operations to work with database table 'order_details'
 */
public class OrderDetailsDAO {
    static final Logger LOGGER = Logger.getLogger(OrderDetailsDAO.class);

    private static final int NO_ROWS_AFFECTED = 0;
    private static final String ORDER_DETAILS_ID = "order_details_id";
    private static final String ORDER_DATE = "order_date";
    private static final String ORDER_TOTAL_AMOUNT = "total_amount";
    private static final String ORDER_STATUS = "order_status";
    private static final String USER_ID = "user_id";

    private static final String INSERT = "INSERT INTO order_details (user_id, order_date, total_amount, order_status) VALUES (?, ?, ?, ?) RETURNING order_details_id";
    private static final String SELECT_BY_USER = "SELECT * FROM order_details WHERE user_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM order_details";
    private static final String SELECT_BY_ID = "SELECT * FROM order_details WHERE order_details_id = ?";
    private static final String UPDATE_STATUS = "UPDATE order_details SET order_status=? WHERE order_details_id = ?";
    private static final String DELETE = "DELETE FROM order_details WHERE order_details_id = ?";

    public static long insertOrder(OrderDetails order) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(INSERT);
            ps.setLong(1, order.getUser().getId());
            ps.setString(2, order.getOrderDate());
            ps.setDouble(3, order.getTotalAmount());
            ps.setShort(4, order.getOrderStatus());
            rs = ps.executeQuery();
            long orderDetailsId = 0;
            if(rs.next()) {
                orderDetailsId = rs.getLong(ORDER_DETAILS_ID);
            }
            return orderDetailsId;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<OrderDetails> getOrdersByUserId(long userId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SELECT_BY_USER);
            ps.setLong(1, userId);
            rs = ps.executeQuery();
            OrderDetails orderDetails = null;
            ArrayList<OrderDetails> orders = new ArrayList<>();
            while(rs.next()) {
                orderDetails = new OrderDetails();
                orderDetails.setOrderId(rs.getLong(ORDER_DETAILS_ID));
                orderDetails.setOrderDate(rs.getString(ORDER_DATE));
                orderDetails.setTotalAmount(rs.getDouble(ORDER_TOTAL_AMOUNT));
                orderDetails.setOrderStatus(rs.getShort(ORDER_STATUS));
                orders.add(orderDetails);
            }
            return orders;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
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
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ALL);
            OrderDetails orderDetails = null;
            ArrayList<OrderDetails> orders = new ArrayList<>();
            while (rs.next()) {
                orderDetails = new OrderDetails();
                orderDetails.setOrderId(rs.getLong(ORDER_DETAILS_ID));
                orderDetails.setOrderDate(rs.getString(ORDER_DATE));
                orderDetails.setTotalAmount(rs.getDouble(ORDER_TOTAL_AMOUNT));
                orderDetails.setOrderStatus(rs.getShort(ORDER_STATUS));
                orders.add(orderDetails);
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
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
        try {
            ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, orderDetailsId);
            rs = ps.executeQuery();
            OrderDetails orderDetails = null;
            User user = null;
            while(rs.next()) {
                orderDetails = new OrderDetails();
                orderDetails.setOrderId(rs.getLong(ORDER_DETAILS_ID));
                orderDetails.setOrderDate(rs.getString(ORDER_DATE));
                orderDetails.setTotalAmount(rs.getDouble(ORDER_TOTAL_AMOUNT));
                orderDetails.setOrderStatus(rs.getShort(ORDER_STATUS));
                user = UserDAO.getUserById(rs.getLong(USER_ID));
                orderDetails.setUser(user);
            }
            return orderDetails;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
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
        try {
            ps = connection.prepareStatement(UPDATE_STATUS);
            ps.setShort(1, status);
            ps.setLong(2, orderDetailsId);
            return ps.executeUpdate();
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int deleteById(long orderDetailsId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(DELETE);
            ps.setLong(1, orderDetailsId);
            return ps.executeUpdate();
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
