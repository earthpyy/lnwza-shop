package ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import application.entity.Product;
import application.entity.ProductDetail;
import application.entity.ProductType;
import application.handler.ProductHandler;

/**
 *
 * @author SE-lnwza
 */
public class StockViewController {
    
    @FXML
    private Button bt_add;

    @FXML
    private Button bt_edit;

    @FXML
    private Button bt_update;

    @FXML
    private Button bt_delete;
    
    @FXML
    private TableView<Product> tableview;

    @FXML
    private TableColumn<Product, ?> tb_checkbox;

    @FXML
    private TableColumn<Product, String> tb_id;

    @FXML
    private TableColumn<Product, ImageView> tb_photo;

    @FXML
    private TableColumn<Product, String> tb_name;

    @FXML
    private TableColumn<ProductType, String> tb_type;

    @FXML
    private TableColumn<ProductDetail, Integer> tb_size;

    @FXML
    private TableColumn<Product, Double> tb_price;
    
    @FXML
    private Label lb_summary;
     
    @FXML
    protected void initialize() {
        tb_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tb_photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        tb_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tb_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tb_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        tb_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        ProductHandler.load();
        ObservableList<Product> data = FXCollections.observableArrayList(ProductHandler.getData());
        tableview.setItems(data);
    }
}
