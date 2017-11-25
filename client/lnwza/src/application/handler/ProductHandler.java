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
    
    public static ArrayList<Product> getData() {
        return products;
    }
    
    public static ArrayList<Product> getRecommendData() {
        ArrayList<Product> result = new ArrayList<>();
        for (int i = products.size() - 1; i >= 0; i--) {
            if (products.get(i).isRecommended()) {
                result.add(products.get(i));
            }
            
            if (result.size() == 3) {
                break;
            }
        }
        
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
        
        EntityManager em = DatabaseConnection.getEM();
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
    
    public static void updateDetail(ProductDetail detail) {
        EntityManager em = DatabaseConnection.getEM();
        ProductDetail origin = em.find(ProductDetail.class, detail.getId());
        em.getTransaction().begin();
        
        if (detail.getQuantity() != origin.getQuantity()) {
            origin.setQuantity(detail.getQuantity());
        } else {
            // TODO: next sprint
        }
        
        em.getTransaction().commit();
        em.close();
    }
    
    public static void delete(Product product) {
        // TODO: next sprint
    }
    
}
