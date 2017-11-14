package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.Session;
import application.entity.User;

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
    
    public static void add(String firstName, String lastName, String username, String password) {
        // TODO: next sprint
    }
    
    public static void update(Long id, String firstName, String lastName, String username, String password) {
        // TODO: next sprint
    }
    
    public static void delete(Long id) {
        // TODO: next sprint
    }
}
