package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SE-lnwza
 */
public class DatabaseConnection {
    private static EntityManagerFactory emf;
    
    public static void load() {
        try {
            emf = Persistence.createEntityManagerFactory("objectdb://" + AppProperties.getDBIP() + ":" + AppProperties.getDBPort() + "/" + AppProperties.getDBName() + ".odb;user=" + AppProperties.getDBUser() + ";password=" + AppProperties.getDBPass());
        } finally {
            System.out.println("Database connected!");
        }
    }
    
    public static EntityManagerFactory getEMF() {
        return emf;
    }
    
    public static EntityManager getEM() {
        return getEMF().createEntityManager();
    }
    
    public static boolean isConnected() {
        return emf.isOpen();
    }
    
    public static void close() {
        emf.close();
    }
}
