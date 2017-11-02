package ui.controller;

import application.entity.Order;
import application.handler.OrderHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author pompr_000
 */
public class OrderViewOwnerController {
    @FXML
    private TextField tf_search;
     
    @FXML
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, String> tb_date;

    @FXML
    private TableColumn<Order, Integer> tb_orderno;

    @FXML
    private TableColumn<Order, String> tb_name;

    @FXML
    private TableColumn<Order, Integer> tb_status;
    
    @FXML
    protected void initialize() {
        tb_date.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tb_orderno.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_name.setCellValueFactory(new PropertyValueFactory<>("agent"));
        tb_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        
        OrderHandler.load();
        ObservableList<Order> data = FXCollections.observableArrayList(OrderHandler.getData());
        tableView.setItems(data);
    }
}
