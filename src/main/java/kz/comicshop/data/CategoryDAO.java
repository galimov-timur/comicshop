package kz.comicshop.data;

import kz.comicshop.entity.Category;
import kz.comicshop.util.DbUtility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * CategoryDAO class contains basic operations to work with database table 'categories'
 */
public class CategoryDAO {

    static final Logger LOGGER = Logger.getLogger(CategoryDAO.class);

    private static final int NO_ROWS_AFFECTED = 0;
    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME = "category_name";

    private static final String INSERT_QUERY = "INSERT INTO Categories (category_name) VALUES (?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM categories";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM categories WHERE category_id=?";
    private static final String DELETE_QUERY = "DELETE FROM categories WHERE category_id = ?";

    public static int insertCategory(Category category) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(INSERT_QUERY);
            ps.setString(1, category.getName());
            return ps.executeUpdate();
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
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
                category.setId(rs.getLong(CATEGORY_ID));
                category.setName(rs.getString(CATEGORY_NAME));
                categories.add(category);
            }
            return categories;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
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
                category.setId(resultSet.getLong(CATEGORY_ID));
                category.setName(resultSet.getString(CATEGORY_NAME));
            }
            return category;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
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
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
