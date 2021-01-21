package kz.comicshop.data;

import kz.comicshop.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public static int insertCategory(Category category) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO Categories (category_name) VALUES (?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, category.getName());
            return ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
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

        String query = "SELECT * FROM categories;";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
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
            System.out.println(e.getMessage());
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

        String query = "SELECT * FROM categories WHERE category_id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
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
            System.out.println(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(resultSet);
            DbUtility.closeStatement(preparedStatement);
            pool.freeConnection(connection);
        }
    }
}
