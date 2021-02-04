package kz.comicshop.data;

import kz.comicshop.entity.User;
import kz.comicshop.util.DbUtility;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserDAO class contains basic operations to work with database table 'users'
 */
public class UserDAO {
    static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    private static final int NO_ROWS_AFFECTED = 0;
    private static final String USER_ID = "user_id";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_SALT = "salt";
    private static final String USER_PHONE = "phone";
    private static final String USER_ROLE = "role";
    private static final String USER_ADDRESS1 = "address1";
    private static final String USER_ADDRESS2 = "address2";
    private static final String USER_CITY = "city";
    private static final String USER_ZIP = "zip";
    private static final String USER_COUNTRY = "country";

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
                userId = rs.getLong(USER_ID);
            }
            return userId;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
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
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
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
            LOGGER.error(e.getMessage());
            return NO_ROWS_AFFECTED;
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
                user.setId(rs.getLong(USER_ID));
                user.setFirstName(rs.getString(USER_FIRST_NAME));
                user.setLastName(rs.getString(USER_LAST_NAME));
                user.setEmail(rs.getString(USER_EMAIL));
                user.setPassword(rs.getString(USER_PASSWORD));
                user.setSalt(rs.getString(USER_SALT));
                user.setPhone(rs.getString(USER_PHONE));
                user.setRole(rs.getShort(USER_ROLE));
            }
            return user;
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
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
                user.setId(rs.getLong(USER_ID));
                user.setFirstName(rs.getString(USER_FIRST_NAME));
                user.setLastName(rs.getString(USER_LAST_NAME));
                user.setEmail(rs.getString(USER_EMAIL));
                user.setPassword(rs.getString(USER_PASSWORD));
                user.setSalt(rs.getString(USER_SALT));
                user.setPhone(rs.getString(USER_PHONE));
                user.setRole(rs.getShort(USER_ROLE));
                user.setAddress1(rs.getString(USER_ADDRESS1));
                user.setAddress2(rs.getString(USER_ADDRESS2));
                user.setCity(rs.getString(USER_CITY));
                user.setZip(rs.getString(USER_ZIP));
                user.setCountry(rs.getString(USER_COUNTRY));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            DbUtility.closeResultSet(rs);
            DbUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
