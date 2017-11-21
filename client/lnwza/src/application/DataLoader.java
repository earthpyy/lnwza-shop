package application;

import application.handler.*;

/**
 *
 * @author SE-lnwza
 */
public class DataLoader {
    
    public static void load() {
        ProductTypeHandler.load();
        ProductHandler.load();
//        OrderHandler.load();
        AgentHandler.load();
//        TransactionHandler.load();
        UserHandler.load();
    }
    
    public static void reload() {
        System.out.println("Reloading data...");
        load();
        System.out.println("Done loading.");
    }
    
}
