package kz.comicshop.data;

import org.apache.log4j.Logger;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Connection pool implementation allows to reuse a number of existing connections,
 * thus boosting application performance. Provides methods to get connection pool,
 * get and release connection.
 */
public class ConnectionPool {
    static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/comicshop");
        } catch(NamingException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static  synchronized ConnectionPool getInstance() {
        if(pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());;
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());;
        }
    }
}
