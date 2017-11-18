package application;

import application.entity.BagProduct;
import application.entity.ProductDetail;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SE-lnwza
 */
public class Bag {
    
    private static ArrayList<BagProduct> items = new ArrayList<>();
    
    public static ArrayList<BagProduct> getItems() {
        return items;
    }
    
    public static BagProduct getBagProductFromDetail(ProductDetail product) {
        for (BagProduct item : items) {
            if (item.getDetail().equals(product)) {
                return item;
            }
        }
        return null;
    }
    
    public static void add(BagProduct product) {
        BagProduct item = getBagProductFromDetail(product.getDetail());
        if (item == null) {
            items.add(product);
        } else {
            updateQuantity(item, product.getQuantity());
        }
    }
    
    public static void add(ProductDetail product, Integer quantity) {
        add(new BagProduct(product, quantity));
    }
    
    public static void remove(BagProduct product) {
        items.remove(product);
    }
    
    public static void updateQuantity(BagProduct item, Integer quantity) {
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        }
    }
    
}
