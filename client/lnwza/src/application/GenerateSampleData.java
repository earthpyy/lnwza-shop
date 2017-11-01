package application;

import application.entity.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            
            System.out.println("Inserting new sample data...");
            FileReader f;
            BufferedReader buff;
            String line = "";
            String[] arr = null, arrt = null, arrd = null;
            int countT, countD;

            // Agents
            Agent ag;
            em.getMetamodel().entity(Agent.class);
            em.createQuery("DELETE FROM Agent").executeUpdate();
            f = new FileReader("sample_data/sample_agent.txt");
            buff = new BufferedReader(f);
            
            while ((line = buff.readLine()) != null) {
                arr = line.split(",");
                ag = new Agent(arr[0], arr[1], arr[2], arr[3]);
                em.persist(ag);
            }
                        
            // Products
            Product pd;
            ProductType pdt;
            em.getMetamodel().entity(Product.class);
            em.createQuery("DELETE FROM Product").executeUpdate();
            em.getMetamodel().entity(ProductDetail.class);
            em.createQuery("DELETE FROM ProductDetail").executeUpdate();
            em.getMetamodel().entity(ProductType.class);
            em.createQuery("DELETE FROM ProductType").executeUpdate();
            
            f = new FileReader("sample_data/sample_product.txt");
            buff = new BufferedReader(f);
            
            while ((line = buff.readLine()) != null) {
                arr = line.split(",");
                countT = Integer.parseInt(arr[1]);
                pdt = new ProductType(arr[0]);
                em.persist(pdt);
                
                for (int i = 0; i < countT; i++) {
                    arr = buff.readLine().split(",");
                    countD = Integer.parseInt(arr[4]);
                    
                    pd = new Product(arr[0], arr[1], arr[2], "sample_data/" + arr[3], pdt, arr[5], Double.parseDouble(arr[6]));
                    em.persist(pd);
                    
                    for (int j = 0; j < countD; j++) {
                        arrd = buff.readLine().split(",");
                        em.persist(new ProductDetail(pd, arrd[0], arrd[1], Integer.parseInt(arrd[2])));
                    }
                }
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
