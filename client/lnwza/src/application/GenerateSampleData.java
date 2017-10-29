package application;

import application.entity.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
            String[] arr = null, arrd = null;
            int count;

            // Agents
            Agent ag;
            em.getMetamodel().entity(application.entity.Agent.class);
            em.createQuery("DELETE FROM Agent").executeUpdate();
            f = new FileReader("sample_data/sample_agent.txt");
            buff = new BufferedReader(f);
            
            while ((line = buff.readLine()) != null) {
                arr = line.split(",");
                ag = new Agent(arr[0], arr[1], arr[2], arr[3]);
                em.persist(ag);
            }
            
            // Product Types
            ArrayList<ProductType> pdt = new ArrayList<>();
            em.getMetamodel().entity(application.entity.ProductType.class);
            em.createQuery("DELETE FROM ProductType").executeUpdate();
            f = new FileReader("sample_data/sample_producttype.txt");
            buff = new BufferedReader(f);
            
            line = buff.readLine();
            arr = line.split(",");
            for (String arri : arr) {
                ProductType temp = new ProductType(arri);
                pdt.add(temp);
                em.persist(temp);
            }
            
            // Products
            Product pd;
            em.getMetamodel().entity(application.entity.Product.class);
            em.createQuery("DELETE FROM Product").executeUpdate();
            em.getMetamodel().entity(application.entity.ProductDetail.class);
            em.createQuery("DELETE FROM ProductDetail").executeUpdate();
            
            f = new FileReader("sample_data/sample_product.txt");
            buff = new BufferedReader(f);
            
            while ((line = buff.readLine()) != null) {
                arr = line.split(",");
                count = Integer.parseInt(arr[5]);
                ProductDetail[] pdd = new ProductDetail[count];
                
                for (int i = 0; i < count; i++) {
                    arrd = buff.readLine().split(",");
                    pdd[i] = new ProductDetail(arrd[0], arrd[1], Integer.parseInt(arrd[2]));
                    em.persist(pdd[i]);
                }
                
                pd = new Product(arr[0], arr[1], arr[2], arr[3], pdt.get(Integer.parseInt(arr[4])), pdd, arr[6], Double.parseDouble(arr[7]));
                em.persist(pd);
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
