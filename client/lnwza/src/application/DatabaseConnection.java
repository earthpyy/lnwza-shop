package application;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SE-lnwza
 */
public class DatabaseConnection {
    private static EntityManagerFactory emf;
    
    public static void load() {
        emf = Persistence.createEntityManagerFactory("objectdb://" + AppProperties.getDBIP() + ":" + AppProperties.getDBPort() + "/" + AppProperties.getDBName() + ".odb;user=" + AppProperties.getDBUser() + ";password=" + AppProperties.getDBPass() + "");
        System.out.println(emf.isOpen());
    }
    
    public static EntityManagerFactory getConnection() {
        return emf;
    }
}
