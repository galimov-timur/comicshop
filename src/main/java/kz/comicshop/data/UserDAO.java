package kz.comicshop.data;

import kz.comicshop.entity.User;
import kz.comicshop.util.DbUtility;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    static final Logger logger = Logger.getLogger(UserDAO.class);

    private static final String INSERT = "INSERT INTO Users (first_name, last_name, email, password, salt, phone) VALUES (?, ?, ?, ?, ?, ?) RETURNING user_id";
    private static final String UPDATE_ADDRESS = "UPDATE Users SET address1=?, address2=?, city=?, zip=?, country=? WHERE email = ?";
    private static final String DELETE = "DELETE FROM users WHERE email = ?";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE user_id = ?";

    public static long insertUser(User user) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getSalt());
            ps.setString(6, user.getPhone());
            rs = ps.executeQuery();
            long userId = 0;
            if(rs.next()){
                userId = rs.getLong("user_id");
            }
            return userId;
        } catch(SQLException e) {
            logger.error(e.getMessage());
            return 0;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int updateAddress(User user) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(UPDATE_ADDRESS);
            ps.setString(1, user.getAddress1());
            ps.setString(2, user.getAddress2());
            ps.setString(3, user.getCity());
            ps.setString(4, user.getZip());
            ps.setString(5, user.getCountry());
            ps.setString(6, user.getEmail());
            return ps.executeUpdate();
        } catch(SQLException e) {
            logger.error(e.getMessage());
            return 0;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int delete(User user) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(DELETE);
            ps.setString(1, user.getEmail());
            return ps.executeUpdate();
        } catch(SQLException e) {
            logger.error(e.getMessage());
            return 0;
        } finally {
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static User getUserByEmail(String email) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(SELECT_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if(rs.next()) {
                user = new User();
                user.setId(rs.getLong("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getShort("role"));
            }
            return user;
        } catch(SQLException e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static User getUserById(long userId) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, userId);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getShort("role"));
                user.setAddress1(rs.getString("address1"));
                user.setAddress2(rs.getString("address2"));
                user.setCity(rs.getString("city"));
                user.setZip(rs.getString("zip"));
                user.setCountry(rs.getString("country"));
            }
            return user;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
