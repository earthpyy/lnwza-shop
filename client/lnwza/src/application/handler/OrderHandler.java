package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.entity.Order;
import application.entity.OrderStatus;

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
    
    public static Order getDataFromId(Long id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
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
    
    public static void update(Order order) {
        EntityManager em = emf.createEntityManager();
        Order origin = em.find(Order.class, order.getId());
        em.getTransaction().begin();
        
        if (order.getLastStatus() != origin.getLastStatus()) {
            origin.addStatus(order.getLastStatus());
            // TODO: change getLastStatus() to OrderStatus.class
            if (order.getLastStatus().getStatus() == OrderStatus.PACKING) {
                // TODO: add to delivery
            }
        } else {
            // TODO: next sprint
        }
        
        em.getTransaction().commit();
    }
}
