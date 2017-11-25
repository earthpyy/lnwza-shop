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
        
        EntityManager em = DatabaseConnection.getEM();
        try {
            TypedQuery<ProductDetail> q = em.createQuery("SELECT FROM ProductDetail", ProductDetail.class);
            for (ProductDetail pd : q.getResultList()) {
                pds.add(pd);
            }
        } finally {
            em.close();
        }
    }

    public static void add(ProductDetail detail) {
        // TODO: next sprint
    }
    
    public static void update(ProductDetail detail) {
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
    
    public static void delete(ProductDetail detail) {
        // TODO: next sprint
    }
    
}
