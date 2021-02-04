package kz.comicshop.data;

import kz.comicshop.entity.Product;
import kz.comicshop.util.DbUtility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * ProductDAO class contains basic operations to work with database table 'products'
 */
public class ProductDAO {
    static final Logger LOGGER = Logger.getLogger(ProductDAO.class);

    private  static final int NO_ROWS_AFFECTED = 0;
    private static final String PRODUCT_ID = "product_id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_PRICE = "product_price";
    private static final String PRODUCT_DESC = "product_desc";
    private static final String PRODUCT_IMAGE_URL = "product_img_url";
    private static final String PRODUCT_QUANTITY= "product_quantity";

    private static final String INSERT = "INSERT INTO Products (category_id, product_name, product_desc, product_price, product_img_url, product_quantity) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_CATEGORY = "SELECT * FROM products WHERE category_id=?";
    private static final String SELECT = "SELECT * FROM products WHERE product_id = ?";
    private static final String DELETE = "DELETE FROM products WHERE product_id = ?";

    public static int insertProduct(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(INSERT);
            ps.setLong(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getImageUrl());
            ps.setInt(6, product.getQuantity());
            return ps.executeUpdate();
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Product> getProductsByCategory(long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SELECT_BY_CATEGORY);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            Product product = null;
            while(rs.next()) {
                product = new Product();
                product.setId(rs.getLong(PRODUCT_ID));
                product.setName(rs.getString(PRODUCT_NAME));
                product.setPrice(rs.getDouble(PRODUCT_PRICE));
                product.setImageUrl(rs.getString(PRODUCT_IMAGE_URL));
                product.setQuantity(rs.getInt(PRODUCT_QUANTITY));
                products.add(product);
            }
            return products;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closeStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static Product getProductById(long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SELECT);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Product product = null;
            if(rs.next()) {
                product = new Product();
                product.setId(rs.getLong(PRODUCT_ID));
                product.setName(rs.getString(PRODUCT_NAME));
                product.setDescription(rs.getString(PRODUCT_DESC));
                product.setPrice(rs.getDouble(PRODUCT_PRICE));
                product.setImageUrl(rs.getString(PRODUCT_IMAGE_URL));
                product.setQuantity(rs.getInt(PRODUCT_QUANTITY));
            }
            return product;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int delete(long productId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(DELETE);
            ps.setLong(1, productId);
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
