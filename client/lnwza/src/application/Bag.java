package application;

import application.entity.BagProduct;
import application.entity.ProductDetail;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SE-lnwza
 */
public class Bag {
    
    private static ObservableList<BagProduct> items = FXCollections.observableArrayList();
    private static Payment payment;
    
    public static ObservableList<BagProduct> getItems() {
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
    
    public static Integer getAmount() {
        return items.size();
    }
    
    public static IntegerBinding getAmountProperty() {
        return Bindings.size(items);
    }
    
    public static Payment getPayment() {
        return payment;
    }
    
    public static void setPayment(Payment newPayment) {
        payment = newPayment;
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
