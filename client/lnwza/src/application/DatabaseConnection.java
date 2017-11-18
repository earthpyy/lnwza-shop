package application;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SE-lnwza
 */
public class DatabaseConnection {
    private static EntityManagerFactory emf;
    // TODO: check with multiuser
    
    public static void load() {
        try {
            emf = Persistence.createEntityManagerFactory("objectdb://" + AppProperties.getDBIP() + ":" + AppProperties.getDBPort() + "/" + AppProperties.getDBName() + ".odb;user=" + AppProperties.getDBUser() + ";password=" + AppProperties.getDBPass());
        } finally {
            System.out.println("Database connected!");
        }
    }
    
    public static EntityManagerFactory getConnection() {
        return emf;
    }
    
    public static boolean isConnected() {
        return emf.isOpen();
    }
    
    public static void close() {
        emf.close();
    }
}
