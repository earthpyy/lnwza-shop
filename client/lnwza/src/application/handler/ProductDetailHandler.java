package application.handler;

import application.DatabaseConnection;
import application.entity.Product;
import application.entity.ProductDetail;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author SE-lnwza
 */
// TODO: is this useless?
public class ProductDetailHandler {
    
    private static ArrayList<ProductDetail> pds;
    private static final EntityManagerFactory emf = DatabaseConnection.getConnection();
    
    public static ArrayList<ProductDetail> getData() {
        return pds;
    }
    
//    public static ProductDetail getDataFromColor() {
//        
//    }

//    public static void load(ArrayList<ProductDetail> pdArr) {
//        pds = new ArrayList<>();
//        
//        EntityManager em = emf.createEntityManager();
//        for (ProductDetail pd : pdArr) {
//            TypedQuery<ProductDetail> q = em.createQuery("SELECT pd FROM ProductDetail pd WHERE pd.id = :id", ProductDetail.class);
//            q = q.setParameter("id", pd.getId());
//            for (ProductDetail pdd : q.getResultList()) { 
//                pds.add(pdd);
//            }
//        }
//        em.close();
//    }
    
    // TODO: test this
    public static ArrayList<ProductDetail> getDataFromProduct(Product product) {
        ArrayList<ProductDetail> result = new ArrayList<>();
        for (ProductDetail pd : pds) {
            if (product == pd.getProduct()) {
                result.add(pd);
            }
        }
        return result;
    }
    
    public static void load() {
        pds = new ArrayList<>();
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<ProductDetail> q = em.createQuery("SELECT FROM ProductDetail", ProductDetail.class);
            for (ProductDetail pd : q.getResultList()) {
                pds.add(pd);
            }
        } finally {
            em.close();
        }
    }
    
    // recheck these methods
    public static void add(String colorName, String color, Integer quantity) {
        // TODO: next sprint
    }
    
    public static void update(Long id, String colorName, String color, Integer quantity) {
        // TODO: next sprint
    }
    
    public static void delete(Long id) {
        // TODO: next sprint
    }
    
}
