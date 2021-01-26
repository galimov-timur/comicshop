package kz.comicshop.data;

import kz.comicshop.entity.Category;
import kz.comicshop.util.DbUtility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDAO {

    static final Logger logger = Logger.getLogger(CategoryDAO.class);

    private final static String INSERT_QUERY = "INSERT INTO Categories (category_name) VALUES (?)";
    private final static String SELECT_ALL_QUERY = "SELECT * FROM categories";
    private final static String SELECT_ONE_QUERY = "SELECT * FROM categories WHERE category_id=?";
    private final static String DELETE_QUERY = "DELETE FROM categories WHERE category_id = ?";

    public static int insertCategory(Category category) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(INSERT_QUERY);
            ps.setString(1, category.getName());
            return ps.executeUpdate();
        } catch(SQLException e) {
            logger.error(e.getMessage());
            return 0;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Category> getCategories() {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ALL_QUERY);
            ArrayList<Category> categories = new ArrayList<>();
            Category category = null;
            while(rs.next()) {
                category = new Category();
                category.setId(rs.getLong("category_id"));
                category.setName(rs.getString("category_name"));
                categories.add(category);
            }
            return categories;
        } catch(SQLException e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closeStatement(statement);
            pool.freeConnection(connection);
        }
    }

    public static Category getCategoryById(Long id) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            Category category = null;
            if(resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getLong("category_id"));
                category.setName(resultSet.getString("category_name"));
            }
            return category;
        } catch(SQLException e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(resultSet);
            DbUtility.closeStatement(preparedStatement);
            pool.freeConnection(connection);
        }
    }

    public static int delete(long categoryId) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(DELETE_QUERY);
            ps.setLong(1, categoryId);
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
