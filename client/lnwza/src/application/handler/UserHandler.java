package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.Session;
import application.entity.Agent;
import application.entity.Owner;
import application.entity.User;
import java.util.Date;

/**
 *
 * @author SE-lnwza
 */
public class UserHandler {
    private static ArrayList<User> users;
    private static final EntityManagerFactory emf = DatabaseConnection.getConnection();
    
    public static ArrayList<User> getData() {
        return users;
    }
    
    public static User getUser(String username, String password) {
        if (Session.isLoggedIn())
            return null;
        
        User result = null;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.isCorrectPassword(password)) {
                result = user;
                break;
            }
        }
        return result;
    }
    
    public static ArrayList<Agent> getAgents() {
        ArrayList<Agent> result = new ArrayList<>();
        for (User user : users) {
            if (!isOwner(user)) {
                result.add(user.toAgent());
            }
        }
        return result;
    }
    
    public static ArrayList<Owner> getOwners() {
        ArrayList<Owner> result = new ArrayList<>();
        for (User user : users) {
            if (isOwner(user)) {
                result.add(user.toOwner());
            }
        }
        return result;
    }

    public static void load() {
        users = new ArrayList<>();
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<User> q = em.createQuery("SELECT FROM User", User.class);
            for (User user : q.getResultList()) {
                users.add(user);
            }
        } finally {
            em.close();
        }
    }
    
    public static boolean isOwner(User user) {
        return (user instanceof Owner);
    }
    
    public static void add(User user) {
        // TODO: next sprint
    }
    
    public static void updateLoggedIn(Owner user) {
        EntityManager em = emf.createEntityManager();
        Owner origin = em.find(Owner.class, user.getId());
        em.getTransaction().begin();

        origin.setLastLoggedIn(new Date());
        
        em.getTransaction().commit();
        em.close();
    }
    
    public static void delete(User user) {
        // TODO: next sprint
    }
}
