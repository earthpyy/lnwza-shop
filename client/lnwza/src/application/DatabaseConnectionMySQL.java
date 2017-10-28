package application;

import java.sql.*;

/**
 *
 * @author SE-lnwza
 */
public class DatabaseConnectionMySQL {
    private static String url = "jdbc:mysql://" + AppProperties.getDBIP() + ":3306/" + AppProperties.getDBName() + "?autoReconnect=true&useSSL=false";      
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, AppProperties.getDBUser(), AppProperties.getDBPass());
//            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println("Failed to connect database!"); 
            e.printStackTrace();
        }
        return con;
    }
    
    public static boolean checkConnection() {
        return (con != null);
    }
}
