package ui.controller;
import application.Bag;
import application.SceneLoader;
import application.entity.Product;
import application.entity.ProductDetail;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
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
    private ComboBox<ProductDetail> cb_color;

    @FXML
    private ComboBox<Integer> cb_qty;

    @FXML
    private Button bt_add;

    @FXML
    private Button bt_cancel;

    @FXML
    void add(ActionEvent event) {
        ProductDetail product = cb_color.getSelectionModel().getSelectedItem();
        Bag.add(product, cb_qty.getValue());
        SceneLoader.closePopup();
    }

    @FXML
    void close(ActionEvent event) {
        SceneLoader.closePopup();
    }
    
    void fill(Product product) {
        im_product.setImage(product.getPhoto());
        lb_name.setText(product.getName());
        lb_price.setText("฿" + product.getPrice());
        
        ObservableList<ProductDetail> colorData = FXCollections.observableArrayList(product.getDetail());
        cb_color.setItems(colorData);
        cb_color.getSelectionModel().selectFirst();
        Callback<ListView<ProductDetail>, ListCell<ProductDetail>> factory = lv -> new ListCell<ProductDetail>() {
            @Override
            protected void updateItem(ProductDetail item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getColorName());
                    Rectangle rect = new Rectangle(10, 10);
                    rect.setFill(item.getColor());
                    setGraphic(rect);
                }
            }
        };
        cb_color.setCellFactory(factory);
        cb_color.setButtonCell(factory.call(null));
        
        ObservableList<Integer> qtyData = FXCollections.observableArrayList(Arrays.asList(1, 2, 3, 4, 5));
        cb_qty.setItems(qtyData);
        cb_qty.getSelectionModel().selectFirst();
    }
    
}
