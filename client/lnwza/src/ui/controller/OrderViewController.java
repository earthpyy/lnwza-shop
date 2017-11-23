package ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import application.SceneLoader;
import application.entity.BagProduct;
import application.entity.Order;
import application.handler.OrderHandler;
import java.util.List;
import javafx.scene.control.TableCell;

/**
 *
 * @author SE-lnwza
 */
public class OrderViewController {
    @FXML
    private TextField tf_search; // TODO: implement this
     
    @FXML
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, String> tb_date;
    
    @FXML
    private TableColumn<Order, String> tb_time;

    @FXML
    private TableColumn<Order, Integer> tb_orderno;

    @FXML
    private TableColumn<Order, String> tb_name;
    
    @FXML
    private TableColumn<Order, List<BagProduct>> tb_product;
    
    @FXML
    private TableColumn<Order, Double> tb_total;

    @FXML
    private TableColumn<Order, Integer> tb_status;
    
    @FXML
    protected void initialize() {
        tb_date.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tb_time.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        tb_orderno.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_name.setCellValueFactory(new PropertyValueFactory<>("agentName"));
        
        tb_product.setCellValueFactory(new PropertyValueFactory<>("products"));
        tb_product.setCellFactory((TableColumn<Order, List<BagProduct>> col) ->
            new TableCell<Order, List<BagProduct>>() {
                @Override
                public void updateItem(List<BagProduct> products, boolean empty) {
                    super.updateItem(products, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        String text = "";
                        for (BagProduct product : products) {
                            text += "- " + product.getQuantity() + "x " + product.getProductName() + " (" + product.getColorName() + ")\n";
                        }
                        setText(text);
                    }
                }
            }
        );
        
        tb_total.setCellValueFactory(new PropertyValueFactory<>("amount"));
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
        
        OrderHandler.load();
        ObservableList<Order> data = FXCollections.observableArrayList(OrderHandler.getData());
        tableView.setItems(data);
    }

}
