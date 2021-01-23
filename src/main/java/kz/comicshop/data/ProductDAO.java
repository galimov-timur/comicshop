package kz.comicshop.data;

import kz.comicshop.entity.Product;
import kz.comicshop.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    public static int insertProduct(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO Products (category_id, product_name, product_desc, product_price, product_img_url, product_quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, product.getCategory_id());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getImageUrl());
            ps.setInt(6, product.getQuantity());
            return ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Product> getProductsByCategory(long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM products WHERE category_id=?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            Product product = null;
            while(resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("product_price"));
                product.setImageUrl(resultSet.getString("product_img_url"));
                product.setQuantity(resultSet.getInt("product_quantity"));
                products.add(product);
            }
            return products;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(resultSet);
            DbUtility.closeStatement(preparedStatement);
            pool.freeConnection(connection);
        }
    }

    public static Product getProductById(long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM products WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
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
            System.out.println(e);
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

        String query = "DELETE FROM products WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, productId);
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
