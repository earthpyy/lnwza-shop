package ui.controller;

import application.entity.ProductDetail;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
/**
 *
 * @author SE-lnwza
 */
public class PurchaseBagController {
    
    @FXML
    private TableColumn<ProductDetail, ImageView> tb_photo;

    @FXML
    private TableColumn<ProductDetail, String> tb_detail;

    @FXML
    private TableColumn<ProductDetail, Double> tb_price;

    @FXML
    private TableColumn<ProductDetail, Integer> tb_qty;

    @FXML
    private TableColumn<ProductDetail, Double> tb_subtotal;

    @FXML
    private TableColumn<ProductDetail, ?> tb_remove;

    @FXML
    private TextField tf_subtotal;

    @FXML
    private TextField tf_tax;

    @FXML
    private TextField tf_shipping;

    @FXML
    private TextField tf_total;
    
    @FXML
    protected void initialize() {
        
    }
    
}
