package application;

import application.payment.Payment;
import application.entity.BagProduct;
import application.entity.Order;
import application.entity.ProductDetail;
import application.entity.Transaction;
import application.entity.TransactionType;
import application.handler.OrderHandler;
import application.handler.TransactionHandler;
import application.handler.UserHandler;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SE-lnwza
 */
public class Bag {
    
    private static Bag bag;
    
    private ObservableList<BagProduct> items = FXCollections.observableArrayList();
    private Double total = 0.0;
    
    private Bag() {
        // make this private
    }
    
    public static Bag getInstance() {
        if (bag == null) {
            bag = new Bag();
        }
        return bag;
    }
    
    public ObservableList<BagProduct> getItems() {
        return items;
    }
    
    public BagProduct getBagProductFromDetail(ProductDetail product) {
        for (BagProduct item : items) {
            if (item.getDetail().equals(product)) {
                return item;
            }
        }
        return null;
    }
    
    public Integer getAmount() {
        return items.size();
    }
    
    public IntegerBinding getAmountProperty() {
        return Bindings.size(items);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    public void add(BagProduct product) {
        BagProduct item = getBagProductFromDetail(product.getDetail());
        if (item == null) {
            items.add(product);
        } else {
            updateQuantity(item, product.getQuantity());
        }
        total += product.getTotal();
    }
    
    public void add(ProductDetail product, Integer quantity) {
        add(new BagProduct(product, quantity));
    }
    
    public void remove(BagProduct product) {
        items.remove(product);
        total -= product.getTotal();
    }
    
    public void updateQuantity(BagProduct item, Integer quantity) {
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        }
    }
    
    public void reset() {
        items.removeAll(items);
        total = 0.0;
    }
    
}
