package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
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
    private static User currentUser = null;
    
    public static ArrayList<User> getData() {
        return users;
    }
    
    public static User getUser(String username, String password) {
        if (isLoggedIn())
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
        
        EntityManager em = DatabaseConnection.getEM();
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
    
    public static void updateLastLoggedIn(Owner user) {
        EntityManager em = DatabaseConnection.getEM();
        Owner origin = em.find(Owner.class, user.getId());
        em.getTransaction().begin();

        origin.setLastLoggedIn(new Date());
        
        em.getTransaction().commit();
        em.close();
    }
    
    public static void setLoggedIn(User user) {
        EntityManager em = DatabaseConnection.getEM();
        User origin = em.find(User.class, user.getId());
        em.getTransaction().begin();

        origin.setLoggedIn(true);
        
        em.getTransaction().commit();
        em.close();
    }
    
    public static void setLoggedOut(User user) {
        EntityManager em = DatabaseConnection.getEM();
        User origin = em.find(User.class, user.getId());
        em.getTransaction().begin();

        origin.setLoggedIn(false);
        
        em.getTransaction().commit();
        em.close();
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static boolean isLoggedIn() {
        return (getCurrentUser() != null);
    }
    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public static void logIn(User user) {
        setLoggedIn(user);
        setCurrentUser(user);
    }
    
    public static void logOut() {
        if (isLoggedIn()) {
            setLoggedOut(getCurrentUser());
            setCurrentUser(null);
        }
    }
    
    public static void delete(User user) {
        // TODO: next sprint
    }
}
