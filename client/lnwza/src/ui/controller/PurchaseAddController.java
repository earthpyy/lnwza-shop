package ui.controller;
import application.Bag;
import application.SceneLoader;
import application.entity.ImageButton;
import application.entity.Product;
import application.entity.ProductDetail;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
/**
 *
 * @author SE-lnwza
 */
public class PurchaseAddController extends Fillable<Product> {
    
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

//    @FXML
//    private Button bt_add;
//
//    @FXML
//    private Button bt_cancel;
    
    @FXML
    private HBox HBox;
    
    private Product product;
    
    @FXML
    protected void initialize() {
        ImageButton bt_add = new ImageButton("/ui/resources/images/button/ButtonAdd.png");
        bt_add.setOnAction((event) -> {
            add();
        });
        HBox.getChildren().add(bt_add);
    }

    void add() {
        ProductDetail detail = cb_color.getSelectionModel().getSelectedItem();
        Bag.getInstance().add(detail, cb_qty.getValue());
        SceneLoader.closePopup();
    }

    @FXML
    void close(ActionEvent event) {
        SceneLoader.closePopup();
    }
    
    @Override
    void fill(Product pd) {
        set(pd);
        im_product.setImage(product.getPhoto());
        lb_name.setText(product.getName());
        lb_price.setText("$" + product.getPrice());
        
        ObservableList<ProductDetail> colorData = FXCollections.observableArrayList(product.getLeftDetail());
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
        change();
    }
    
    void set(Product pd) {
        product = pd;
    }
    
    @FXML
    void change() {
        int amountLeft = cb_color.getSelectionModel().getSelectedItem().getQuantity();
        ArrayList<Integer> qtyList = new ArrayList<>();
        for (int i = 1; i <= amountLeft && i <= 5; i++)
            qtyList.add(i);
        ObservableList<Integer> qtyData = FXCollections.observableArrayList(qtyList);
        cb_qty.setItems(qtyData);
        cb_qty.getSelectionModel().selectFirst();
    }
    
}
