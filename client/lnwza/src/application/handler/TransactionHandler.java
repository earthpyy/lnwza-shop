package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.entity.Order;
import application.entity.Transaction;

/**
 *
 * @author pompr_000
 */
public class TransactionHandler {
    
   private static ArrayList<Transaction> transaction;
    private static final EntityManagerFactory emf = DatabaseConnection.getConnection();
    
    public static ArrayList<Transaction> getData() {
        return transaction;
    }

    public static void load() {
        transaction = new ArrayList<>();
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Transaction> q = em.createQuery("SELECT FROM Transaction", Transaction.class);
            for (Transaction ts : q.getResultList()) {
                transaction.add(ts);
            }
        } finally {
            em.close();
        }
    }
    
}
