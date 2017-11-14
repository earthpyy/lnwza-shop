package ui.controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import application.SceneLoader;
import application.entity.Order;
import application.handler.OrderHandler;

/**
 *
 * @author SE-lnwza
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
        tb_name.setCellValueFactory(new PropertyValueFactory<>("agentName"));
        tb_status.setCellValueFactory(new PropertyValueFactory<>("lastStatusName"));
        
        tableView.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Order rowData = tableView.getSelectionModel().getSelectedItem();
                    SceneLoader.popup("OrderDetail", "Order #" + rowData.getId());

                    OrderDetailController ctrl = SceneLoader.getPopupController(OrderDetailController.class);
                    ctrl.fill(rowData);
                }
            });
            return row;
        });
        
//        OrderHandler.load();
        ObservableList<Order> data = FXCollections.observableArrayList(OrderHandler.getData());
        tableView.setItems(data);
    }
}
