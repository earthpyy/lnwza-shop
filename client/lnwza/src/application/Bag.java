package application;

import application.entity.ProductDetail;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SE-lnwza
 */
public class Bag {
    
    private static List<ProductDetail> items = new ArrayList<>();
    
    public static void add(ProductDetail product) {
        items.add(product);
    }
    
    public static void remove(ProductDetail product) {
        items.remove(product);
    }
    
}
