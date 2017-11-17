package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.entity.Product;
import application.entity.ProductDetail;
import application.entity.ProductType;

/**
 *
 * @author SE-lnwza
 */
public class ProductHandler {
    
    private static ArrayList<Product> products;
    private static final EntityManagerFactory emf = DatabaseConnection.getConnection();
    
    public static ArrayList<Product> getData() {
        return products;
    }
    
    public static ArrayList<Product> getRecommendData() {
        ArrayList<Product> result = new ArrayList<>();
        // TODO: check from db
        result.add(products.get(0));
        result.add(products.get(1));
        result.add(products.get(2));
        
        return result;
    }

    public static void load() {
        products = new ArrayList<>();
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Product> q = em.createQuery("SELECT FROM Product", Product.class);
            for (Product product : q.getResultList()) {
                products.add(product);
            }
        } finally {
            em.close();
        }
    }
    
    public static void add(String productId, String name, String description, String photo, ProductType type, ProductDetail[] detail, String size, Double price) {
        // TODO: next sprint
    }
    
    public static void update(Long id, String productId, String name, String description, String photo, ProductType type, ProductDetail[] detail, String size, Double price) {
        // TODO: next sprint
    }
    
    public static void delete(Long id) {
        // TODO: next sprint
    }
    
}
