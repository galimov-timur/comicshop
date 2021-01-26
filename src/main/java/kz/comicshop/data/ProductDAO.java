package kz.comicshop.data;

import kz.comicshop.entity.Product;
import kz.comicshop.util.DbUtility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {

    static final Logger logger = Logger.getLogger(ProductDAO.class);

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
            ps.setLong(1, product.getCategory_id());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getImageUrl());
            ps.setInt(6, product.getQuantity());
            return ps.executeUpdate();
        } catch(SQLException e) {
            logger.error(e.getMessage());
            return 0;
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
                product.setId(rs.getLong("product_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setImageUrl(rs.getString("product_img_url"));
                product.setQuantity(rs.getInt("product_quantity"));
                products.add(product);
            }
            return products;
        } catch(SQLException e) {
            logger.error(e.getMessage());
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
                product.setId(rs.getLong("product_id"));
                product.setName(rs.getString("product_name"));
                product.setDescription(rs.getString("product_desc"));
                product.setPrice(rs.getDouble("product_price"));
                product.setImageUrl(rs.getString("product_img_url"));
                product.setQuantity(rs.getInt("product_quantity"));
            }
            return product;
        } catch(SQLException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
            return 0;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
