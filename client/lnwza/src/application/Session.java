package application;

import application.entity.Owner;
import application.entity.User;

/**
 *
 * @author SE-lnwza
 */
public class Session {
    
    private static User currentUser = null;
    // TODO: bag here ArrayList<ProductDetail>
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static String getFullName() {
        return currentUser.getName();
    }
    
    public static boolean isOwner() {
        return (currentUser instanceof Owner);
    }
    
    public static String getRole() {
        return (isOwner() ? "Owner" : "Agent");
    }
    
    public static boolean isLoggedIn() {
        return (currentUser != null);
    }
    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public static void logOut() {
        currentUser = null;
    }
    
}
