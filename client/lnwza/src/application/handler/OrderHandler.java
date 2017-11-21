package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.entity.BagProduct;
import application.entity.Order;
import application.entity.Transaction;
import application.entity.TransactionType;

/**
 *
 * @author SE-lnwza
 */
public class OrderHandler {
    
    private static ArrayList<Order> orders;
    private static final EntityManagerFactory emf = DatabaseConnection.getConnection();
    
    public static ArrayList<Order> getData() {
        return orders;
    }

    public static void load() {
        orders = new ArrayList<>();
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Order> q = em.createQuery("SELECT FROM Order", Order.class);
            for (Order od : q.getResultList()) {
                orders.add(od);
            }
        } finally {
            em.close();
        }
    }
    
    public static void add(Order order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(order);
        
        em.getTransaction().commit();
        em.close();
    }
}
