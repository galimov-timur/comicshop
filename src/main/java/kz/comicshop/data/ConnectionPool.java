package kz.comicshop.data;

import org.apache.log4j.Logger;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {

    static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/comicshop");
        } catch(NamingException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());;
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch(SQLException e) {
            logger.error(e.getMessage());;
        }
    }
}
