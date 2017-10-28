package application;

import application.entity.Agent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author SE-lnwza
 */
public class GenerateSampleData {
    
    private static EntityManagerFactory emf;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        AppProperties.load();
        DatabaseConnection.load();
        emf = DatabaseConnection.getConnection();
        
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.getMetamodel().entity(application.entity.Agent.class);
            
            System.out.println("Clearing database...");
            em.createQuery("DELETE FROM Agent").executeUpdate();
        
            System.out.println("Inserting new sample data...");
            FileReader f;
            BufferedReader buff;
            String line = "";
            String[] arr = null;
            
            // Agent
            Agent ag;
            f = new FileReader("sample_agent.txt");
            buff = new BufferedReader(f);
            
            while ((line = buff.readLine()) != null) {
                arr = line.split(",");
                ag = new Agent(arr[0], arr[1], arr[2], arr[3]);
                em.persist(ag);
            }
            
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            } else {
                em.close();
                System.out.println("Successful!");
            }
        }
        
        DatabaseConnection.close();
        System.out.println("Database connection is closed!");
    }
    
}
