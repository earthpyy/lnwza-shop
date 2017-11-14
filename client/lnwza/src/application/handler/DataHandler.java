package application.handler;

/**
 *
 * @author SE-lnwza
 */
public class DataHandler {
    
    public static void load() {
        ProductHandler.load();
        OrderHandler.load();
        AgentHandler.load();
        TransactionHandler.load();
        UserHandler.load();
    }
    
    public static void reload() {
        System.out.println("Reloading data...");
        load();
        System.out.println("Done loading.");
    }
    
}
