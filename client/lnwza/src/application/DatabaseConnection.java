package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SE-lnwza
 */
public class DatabaseConnection {
    private static EntityManagerFactory emf;
    private static Connection conn;
    
    public static void load() {
        try {
            loadObjectDB();
            loadMySQL();
        } finally {
            System.out.println("Database connected!");
        }
    }
    
    private static void loadObjectDB() {
        emf = Persistence.createEntityManagerFactory("objectdb://" + AppProperties.getDBIP() + ":" + AppProperties.getDBPort() + "/" + AppProperties.getDBName() + ".odb;user=" + AppProperties.getDBUser() + ";password=" + AppProperties.getDBPass());
    }
    
    private static void loadMySQL() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + AppProperties.getSqlIP() + ":" + AppProperties.getSqlPort() + "/" + AppProperties.getSqlName() + "?useSSL=false", AppProperties.getSqlUser(), AppProperties.getSqlPass());
        } catch (SQLException ex) {
        }
    }
    
    public static EntityManagerFactory getEMF() {
        return emf;
    }
    
    public static EntityManager getEM() {
        return getEMF().createEntityManager();
    }
    
    public static Connection getConnection() {
        return conn;
    }
    
    public static boolean isConnected() {
        return emf.isOpen();
    }
    
    public static void close() {
        emf.close();
    }
}
