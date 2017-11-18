package ui.controller;
import application.entity.Product;
import application.handler.ProductHandler;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
/**
 *
 * @author SE-lnwza
 */
public class PurchaseAddController {
    
    @FXML
    private ImageView im_product;

    @FXML
    private Label lb_name;

    @FXML
    private Label lb_price;

    @FXML
    private ComboBox<String> cb_color;

    @FXML
    private ComboBox<Integer> cb_qty;

    @FXML
    private Button bt_add;

    @FXML
    private Button bt_cancel;

    @FXML
    void add(ActionEvent event) {
//        Bag.add();
    }

    @FXML
    void close(ActionEvent event) {
        
    }
    
    void fill(Product product) {
        im_product.setImage(product.getPhoto());
        lb_name.setText(product.getName());
        lb_price.setText("$" + product.getPrice());
        
        ObservableList<String> colorData = FXCollections.observableArrayList(ProductHandler.getColor(product));
        cb_color.setItems(colorData);
        
        ObservableList<Integer> qtyData = FXCollections.observableArrayList(Arrays.asList(1, 2, 3, 4, 5));
        cb_qty.setItems(qtyData);
    }
    
}
