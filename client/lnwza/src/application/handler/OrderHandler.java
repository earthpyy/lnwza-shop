package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.Delivery;
import application.entity.Agent;
import application.entity.Order;
import application.entity.OrderStatus;

/**
 *
 * @author SE-lnwza
 */
public class OrderHandler {
    
    private static ArrayList<Order> orders;
    
    public static ArrayList<Order> getData() {
        return orders;
    }
    
    public static ArrayList<Order> getDataFromAgent(Agent agent) {
        ArrayList<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getAgent().equals(agent)) {
                result.add(order);
            }
        }
        return result;
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
        
        EntityManager em = DatabaseConnection.getEM();
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
        EntityManager em = DatabaseConnection.getEM();
        em.getTransaction().begin();
        
        em.persist(order);
        
        em.getTransaction().commit();
        em.close();
    }
    
    public static void update(Order order) {
        EntityManager em = DatabaseConnection.getEM();
        Order origin = em.find(Order.class, order.getId());
        em.getTransaction().begin();
        
        if (order.getLastStatus() != origin.getLastStatus()) {
            // TODO: change getLastStatus() to OrderStatus.class
            if (order.getLastStatus().getStatus() == OrderStatus.PACKING) {
                String trackNo = Delivery.gainTrackNo(order.getAgent().getPostCode());
                if (!trackNo.isEmpty()) {
                    origin.setTrackNo(trackNo);
                    origin.addStatus(order.getLastStatus());
                } else {
                    System.out.println("Error! Cannot gain track number from Delivery Service");
                }
            }
        } else {
            // TODO: next sprint
        }
        
        em.getTransaction().commit();
        em.close();
    }
}
