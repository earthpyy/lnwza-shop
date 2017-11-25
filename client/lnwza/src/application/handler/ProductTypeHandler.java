package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.entity.ProductType;

/**
 *
 * @author SE-lnwza
 */
public class ProductTypeHandler {
    
    private static ArrayList<ProductType> types;
    
    public static ArrayList<ProductType> getData() {
        return types;
    }
    
    public static void load() {
        types = new ArrayList<>();
        
        EntityManager em = DatabaseConnection.getEM();
        try {
            TypedQuery<ProductType> q = em.createQuery("SELECT FROM ProductType", ProductType.class);
            for (ProductType type : q.getResultList()) {
                types.add(type);
            }
        } finally {
            em.close();
        }
    }
    
}
