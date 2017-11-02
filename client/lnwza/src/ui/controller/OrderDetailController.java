
package ui.controller;

import application.entity.Order;
import application.entity.Product;
import application.entity.ProductDetail;
import application.handler.OrderHandler;
import application.handler.ProductDetailHandler;
import application.handler.ProductHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author SE-lnwza
 */
public class OrderDetailController {
    @FXML
    private TextField tf_orderno;

    @FXML
    private TextField tf_trackingno;

    @FXML
    private Button bt_update;

    @FXML
    private Button bt_cancle;
    
    @FXML
    private TableView<Product> tableViewItem;

    @FXML
    private TableColumn<Product, Integer> tb_id;

    @FXML
    private TableColumn<Product, String> tb_name;

    @FXML
    private TableColumn<Product, Integer> tb_qty;

    @FXML
    private TableColumn<Product, Double> tb_price;
    
    @FXML
    private TableView<Order> tableViewStatus;

    @FXML
    private TableColumn<Order, String> tb_date;

    @FXML
    private TableColumn<Order, Integer> tb_status;
    
     @FXML
    protected void initialize() {
        updateTableView();
        
        tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_name.setCellValueFactory(new PropertyValueFactory<>("products"));
        tb_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tb_price.setCellValueFactory(new PropertyValueFactory<>("status"));
        
//        ProductHandler.load();
//        ObservableList<Product> data = FXCollections.observableArrayList(ProductHandler.getData());
//        tableViewItem.setItems(data);
        
        tb_date.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tb_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
//        OrderHandler.load();
//        ObservableList<Order> data2 = FXCollections.observableArrayList(OrderHandler.getData());
//        tableViewStatus.setItems(data2);
    }
    
    void updateTableView() {
        tableViewItem.setPlaceholder(new Label("No product found!"));
        tableViewStatus.setPlaceholder(new Label("No status found!"));
    }
    
    void fill(Order pd) {
//        ObservableList<Product> data = FXCollections.observableArrayList();
        
        updateTableView();
    }
    
}
