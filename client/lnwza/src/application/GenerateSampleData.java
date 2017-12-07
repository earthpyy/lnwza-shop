package application;

import application.entity.*;
import application.handler.OrderHandler;
import application.handler.ProductHandler;
import application.handler.TransactionHandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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
        
        EntityManager em = DatabaseConnection.getEM();
        try {
            em.getTransaction().begin();
            
            System.out.println("Inserting new sample data...");
            FileReader f;
            BufferedReader buff;
            String line = "";
            String[] arr = null, arrt = null, arrd = null;
            int countT, countD;
            
            // Owners
            Owner ow;
            em.getMetamodel().entity(Owner.class);
            em.createQuery("DELETE FROM Owner").executeUpdate();
            f = new FileReader("sample_data/sample_owner.txt");
            buff = new BufferedReader(f);
            
            while ((line = buff.readLine()) != null) {
                arr = line.split(",");
                ow = new Owner(arr[0], arr[1], arr[2], arr[3]);
                em.persist(ow);
            }

            // Agents
            Agent ag;
            em.getMetamodel().entity(Agent.class);
            em.createQuery("DELETE FROM Agent").executeUpdate();
            f = new FileReader("sample_data/sample_agent.txt");
            buff = new BufferedReader(f);
            
            while ((line = buff.readLine()) != null) {
                arr = line.split(",");
                ag = new Agent(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7]);
                em.persist(ag);
            }
                        
            // Products
            // TODO: ProductType: wallet has space in front of name
            Product pd = null;
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
                    try {
                    if (arr.length == 9) {
                        pd = new Product(arr[0], arr[1], arr[2], "sample_data/" + arr[3], pdt, Double.parseDouble(arr[5]), Double.parseDouble(arr[6]), Double.parseDouble(arr[7]), Double.parseDouble(arr[8]));
                    } else if (arr.length == 10) {
                        pd = new Product(arr[0], arr[1], arr[2], "sample_data/" + arr[3], pdt, Double.parseDouble(arr[5]), Double.parseDouble(arr[6]), Double.parseDouble(arr[7]), Double.parseDouble(arr[8]), Boolean.parseBoolean(arr[9]));
                    }
                    } catch (NumberFormatException ex) {
                    }
                    em.persist(pd);
                    
                    for (int j = 0; j < countD; j++) {
                        arrd = buff.readLine().split(",");
                        ProductDetail pdd = new ProductDetail(pd, arrd[0], arrd[1], Integer.parseInt(arrd[2]));
                        em.persist(pdd);
                    }
                }
            }
            
            // Status & Transaction
            em.getMetamodel().entity(Status.class);
            em.createQuery("DELETE FROM Status").executeUpdate();
            em.getMetamodel().entity(Transaction.class);
            em.createQuery("DELETE FROM Transaction").executeUpdate();
            em.getMetamodel().entity(Order.class);
            em.createQuery("DELETE FROM Order").executeUpdate();
            
            // Orders (Agent #1 brought Product #1-#2)
            // TODO: (NEW) generate many orders using random
//            Order od;
//            Transaction ta;
//            em.getMetamodel().entity(Order.class);
//            em.createQuery("DELETE FROM Order").executeUpdate();
//            em.getMetamodel().entity(BagProduct.class);
//            em.createQuery("DELETE FROM BagProduct").executeUpdate();
//            
//            em.flush();
//            TypedQuery<Agent> query = em.createQuery("SELECT ag FROM Agent ag ORDER BY ag.id", Agent.class);
//            query.setMaxResults(1);
//            ag = query.getSingleResult();
//
//            od = new Order(ag);
//            
//            TypedQuery<ProductDetail> query2 = em.createQuery("SELECT pd FROM ProductDetail pd ORDER BY pd.id", ProductDetail.class);
//            query2.setMaxResults(2);
//            for (ProductDetail pdd : query2.getResultList()) {
//                od.addProduct(new BagProduct(od, pdd, (int)(Math.random() * 10) + 1));
//            }
//            
////            em.persist(od);
//            OrderHandler.add(od);
//            em.flush();
////            ta = new Transaction(od);
////            em.persist(ta);
//            TransactionHandler.add(new Transaction(od));
            
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            } else {
                em.close();
                System.out.println("Successful!");
            }
        }
        ProductHandler.load();
        ProductHandler.addAllToSQL();
        DatabaseConnection.close();
        System.out.println("Database connection is closed!");
    }
    
}
