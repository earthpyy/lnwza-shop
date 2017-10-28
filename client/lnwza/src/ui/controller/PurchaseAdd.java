package ui.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
/**
 *
 * @author SE-lnwza
 */
public class PurchaseAdd {
    
    @FXML
    private ImageView im_product;

    @FXML
    private Label lb_name;

    @FXML
    private Label lb_price;

    @FXML
    private ComboBox<?> cb_color;

    @FXML
    private ComboBox<?> cb_qty;

    @FXML
    private Button bt_add;

    @FXML
    private Button bt_cancel;
}
