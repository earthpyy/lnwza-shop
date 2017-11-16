package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.MyDate;
import application.entity.Transaction;

/**
 *
 * @author SE-lnwza
 */
public class TransactionHandler {
    
    private static ArrayList<Transaction> trans;
    private static final EntityManagerFactory emf = DatabaseConnection.getConnection();
    private static Double total = 0.0;
    
    public static ArrayList<Transaction> getData() {
        return trans;
    }
    
    public static ArrayList<Transaction> getDataFromMonth(int month, int year) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction tran : trans) {
            if (MyDate.getMonth(tran.getTranDate()) == month && MyDate.getYear(tran.getTranDate()) == year)
                result.add(tran);
        }
        return result;
    }
    
    public static ArrayList<Transaction> getDataFromCurrentMonth() {
        return getDataFromMonth(MyDate.getCurrentMonth(), MyDate.getCurrentYear());
    }

    public static void load() {
        trans = new ArrayList<>();
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Transaction> q = em.createQuery("SELECT FROM Transaction", Transaction.class);
            for (Transaction tran : q.getResultList()) {
                total += tran.getAmount();
                tran.setTotal(total);
                trans.add(tran);
            }
        } finally {
            em.close();
        }
    }
    
}
