package kz.comicshop.util;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtility {
    static final Logger LOGGER = Logger.getLogger(DbUtility.class);

    public static void closeStatement(Statement s) {
        try {
            if(s != null) {
                s.close();
            }
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        try {
            if(ps != null) {
                ps.close();
            }
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
            }
        } catch(SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}