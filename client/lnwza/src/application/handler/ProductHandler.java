package application.handler;

import application.AppProperties;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import application.DatabaseConnection;
import application.entity.Product;
import application.entity.ProductDetail;
import application.entity.ProductType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author SE-lnwza
 */
public class ProductHandler {
    
    private static ArrayList<Product> products;
    
    public static ArrayList<Product> getData() {
        return products;
    }
    
    public static ArrayList<Product> getRecommendData() {
        ArrayList<Product> result = new ArrayList<>();
        for (int i = products.size() - 1; i >= 0; i--) {
            if (products.get(i).isRecommended()) {
                result.add(products.get(i));
            }
            
            if (result.size() == 3) {
                break;
            }
        }
        
        return result;
    }
    
    public static Product getDataFromId(Long id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    
    public static ArrayList<Product> getDataFromType(ProductType type) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getType().equals(type)) {
                result.add(product);
            }
        }
        return result;
    }

    public static void load() {
        products = new ArrayList<>();
        
        EntityManager em = DatabaseConnection.getEM();
        try {
            TypedQuery<Product> q = em.createQuery("SELECT FROM Product", Product.class);
            for (Product product : q.getResultList()) {
                products.add(product);
            }
        } finally {
            em.close();
        }
    }
    
    public static void add(Product product) {
        // TODO: next sprint
    }
    
    public static void addAllToSQL() {
        for (Product product : products) {
            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO `products` (`shopId`, `productId`, `photo`, `name`, `size`, `color`, `price`) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, AppProperties.getShopId());
                ps.setString(2, product.getProductId());
                ps.setString(3, "https://lnwza.earthpyy.com/products/" + product.getProductId() + ".jpg");
                ps.setString(4, product.getName());
                ps.setString(5, product.getSize());
                ps.setString(6, product.getAllColorAsString());
                ps.setDouble(7, product.getPrice());
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
        }
    }
    
    public static void update(Product product) {
        // TODO: next sprint
    }
    
    public static void updateDetail(ProductDetail detail) {
        EntityManager em = DatabaseConnection.getEM();
        ProductDetail origin = em.find(ProductDetail.class, detail.getId());
        em.getTransaction().begin();
        
        if (detail.getQuantity() != origin.getQuantity()) {
            origin.setQuantity(detail.getQuantity());
        } else {
            // TODO: next sprint
        }
        
        em.getTransaction().commit();
        em.close();
    }
    
    public static void delete(Product product) {
        // TODO: next sprint
    }
    
}
