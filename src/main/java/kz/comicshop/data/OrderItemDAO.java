package kz.comicshop.data;

import kz.comicshop.entity.OrderDetails;
import kz.comicshop.entity.OrderItem;
import kz.comicshop.entity.Product;
import kz.comicshop.util.DbUtility;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * OrderItemDAO class contains basic operations to work with database table 'order_item'
 */
public class OrderItemDAO {
    static final Logger LOGGER = Logger.getLogger(OrderItemDAO.class);

    private static final int NO_ROWS_AFFECTED = 0;
    private static final String PRODUCT_QUANTITY = "quantity";
    private static final String PRODUCT_ID = "product_id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_PRICE = "product_price";

    private static final String INSERT = "INSERT INTO order_item (order_details_id, product_id, quantity) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ORDER = "SELECT order_item.quantity, products.product_id, products.product_name, products.product_price FROM order_item " +
                                                  "INNER JOIN products ON order_item.product_id = products.product_id " +
                                                  "WHERE order_item.order_details_id = ?";

    public static int insertOrderItems(OrderDetails order, long orderId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        try {
            int itemsInserted = 0;
            for(OrderItem item: order.getOrderItems()) {
                ps = connection.prepareStatement(INSERT);
                ps.setLong(1, orderId);
                ps.setLong(2, item.getProduct().getId());
                ps.setDouble(3, item.getQuantity());
                itemsInserted += ps.executeUpdate();
            }
            return itemsInserted;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
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
        try {
            ps = connection.prepareStatement(SELECT_BY_ORDER);
            ps.setLong(1, orderDetailsId);
            rs = ps.executeQuery();
            OrderItem orderItem = null;
            Product product = null;
            ArrayList<OrderItem> orderItems = new ArrayList<>();
            while(rs.next()) {
                orderItem = new OrderItem();
                orderItem.setQuantity(rs.getInt(PRODUCT_QUANTITY));
                product = new Product();
                product.setId(rs.getLong(PRODUCT_ID));
                product.setName(rs.getString(PRODUCT_NAME));
                product.setPrice(rs.getDouble(PRODUCT_PRICE));
                orderItem.setProduct(product);
                orderItems.add(orderItem);
            }
            return orderItems;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
