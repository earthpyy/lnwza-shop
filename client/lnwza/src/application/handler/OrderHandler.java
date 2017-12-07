package application.handler;

import application.AppProperties;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.Delivery;
import application.entity.Agent;
import application.entity.BagProduct;
import application.entity.Order;
import application.entity.OrderStatus;
import application.entity.Transaction;
import application.entity.TransactionType;
import application.payment.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    
    public static ArrayList<Order> getDataFromRole() {
        if (UserHandler.getCurrentUser().isOwner()) {
            return getData();
        } else {
            return getDataFromAgent(UserHandler.getCurrentUser().toAgent());
        }
    }
    
    public static Order getDataFromId(Long id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
    
    public static Long getLastId() {
        ArrayList<Order> orders = getDataFromAgent(UserHandler.getCurrentUser().toAgent());
        return orders.get(orders.size() - 1).getId();
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
        updateStatus();
    }
    
    public static void updateStatus() {
        for (Order order : orders) {
            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT `status` FROM `status` WHERE `orderId` = ? LIMIT 1");
                ps.setInt(1, order.getId().intValue());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String status = rs.getString("status");
                    if (!order.getLastStatus().name().equals(status)) {
                        order.addStatus(OrderStatus.valueOf(status));
                        update(order);
                    }
                }
            } catch (SQLException ex) {
            }
        }
    }
    
    public static void create(Payment payment) {
        Order order = new Order(UserHandler.getCurrentUser().toAgent(), payment.getAmount());
        for (BagProduct item : payment.getBag().getItems()) {
            order.addProduct(item);
        }
        add(order);
        
        Transaction tran = new Transaction(order.getId().toString(), TransactionType.ORDER, order.getAmount());
        TransactionHandler.add(tran);
        load();
    }
    
    public static void add(Order order) {
        // objectdb
        EntityManager em = DatabaseConnection.getEM();
        em.getTransaction().begin();

        em.persist(order);

        em.getTransaction().commit();
        em.close();
            
        // mysql
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `status` (`shopId`, `orderId`, `status`) VALUES (?, ?, ?)");
            ps.setInt(1, AppProperties.getShopId());
            ps.setInt(2, order.getId().intValue());
            ps.setString(3, order.getLastStatus().name());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
    public static void update(Order order) {
        // objectdb
        EntityManager em = DatabaseConnection.getEM();
        Order origin = em.find(Order.class, order.getId());
        em.getTransaction().begin();
        
        if (order.getLastStatus() != origin.getLastStatus()) {
            origin.addStatus(order.getLastStatus());
            if (order.getLastStatus() == OrderStatus.PACKING) {
                String trackNo = Delivery.gainTrackNo(order.getAgent().getPostCode());
                if (!trackNo.isEmpty()) {
                    origin.setTrackNo(trackNo);
                } else {
                    System.out.println("Error! Cannot retrieve track number from Delivery Service");
                }
            }
        } else {
            // TODO: next sprint
        }
        
        em.getTransaction().commit();
        em.close();
        
        // mysql
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE `status` SET `status` = ? WHERE `orderId` = ?");
            ps.setString(1, order.getLastStatus().name());
            ps.setInt(2, order.getId().intValue());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
}
