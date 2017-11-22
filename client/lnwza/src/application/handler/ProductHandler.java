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
    
    public static Product getDataFromId(Long id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    
    public static ArrayList<Product> getDataFromType(ProductType type) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getType().equals(type)) {
                result.add(product);
            }
        }
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
    
    public static void add(Product product) {
        // TODO: next sprint
    }
    
    public static void update(Product product) {
        // TODO: next sprint
    }
    
    public static void delete(Product product) {
        // TODO: next sprint
    }
    
}
