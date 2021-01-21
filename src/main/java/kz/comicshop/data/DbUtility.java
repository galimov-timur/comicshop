package kz.comicshop.data;

import java.sql.*;

public class DbUtility {

    public static void closeStatement(Statement s) {
        try {
            if(s != null) {
                s.close();
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        try {
            if(ps != null) {
                ps.close();
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}