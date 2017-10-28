package application.handler;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.entity.Agent;

/**
 *
 * @author SE-lnwza
 */
public class AgentHandler {
    
    private static ArrayList<Agent> agents;
    private static final EntityManagerFactory emf = DatabaseConnection.getConnection();
    
    public static ArrayList<Agent> getData() {
        return agents;
    }

    public static void load() {
        agents = new ArrayList<>();
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Agent> q = em.createQuery("SELECT FROM Agent", Agent.class);
            for (Agent ag : q.getResultList()) {
                agents.add(ag);
            }
        } finally {
            em.close();
        }
    }
    
    public static void add(String firstName, String lastName, String address, String tel) {
        // TODO: next sprint
    }
    
    public static void delete(int agentId) {
        // TODO: next sprint
    }
    
}
