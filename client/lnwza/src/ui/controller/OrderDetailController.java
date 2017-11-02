
package ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import application.entity.Order;
import application.entity.ProductDetail;
import application.entity.Status;
import javafx.beans.property.ReadOnlyObjectWrapper;

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
    private TableView<ProductDetail> tableview_item;

    @FXML
    private TableColumn<ProductDetail, Number> tb_id;

    @FXML
    private TableColumn<ProductDetail, String> tb_name;

    @FXML
    private TableColumn<ProductDetail, Integer> tb_qty;

    @FXML
    private TableColumn<ProductDetail, Double> tb_price;
    
    @FXML
    private TableView<Status> tableview_status;

    @FXML
    private TableColumn<Status, String> tb_date;

    @FXML
    private TableColumn<Status, Integer> tb_status;
    
     @FXML
    protected void initialize() {
        updateTableView();
        
        tb_id.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(tableview_item.getItems().indexOf(column.getValue()) + 1));
        tb_name.setCellValueFactory(new PropertyValueFactory<>("products"));
        tb_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tb_price.setCellValueFactory(new PropertyValueFactory<>("status"));
        
//        ProductHandler.load();
//        ObservableList<Product> data = FXCollections.observableArrayList(ProductHandler.getData());
//        tableViewItem.setItems(data);
        
        tb_date.setCellValueFactory(new PropertyValueFactory<>("obtainedDate"));
        tb_status.setCellValueFactory(new PropertyValueFactory<>("statusName"));
        
//        OrderHandler.load();
//        ObservableList<Order> data2 = FXCollections.observableArrayList(OrderHandler.getData());
//        tableViewStatus.setItems(data2);
    }
    
    void updateTableView() {
        tableview_item.setPlaceholder(new Label("No product found!"));
        tableview_status.setPlaceholder(new Label("No status found!"));
    }
    
    void fill(Order pd) {
        ObservableList<ProductDetail> dataItem = FXCollections.observableArrayList(pd.getProducts());
        ObservableList<Status> dataStatus = FXCollections.observableArrayList(pd.getStatus());
        
        tableview_item.setItems(dataItem);
        tableview_status.setItems(dataStatus);
        
        updateTableView();
    }
    
}
