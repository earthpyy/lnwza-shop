
package ui.controller;

import application.DatabaseConnection;
import application.entity.BagProduct;
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
import application.entity.Status;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableCell;
import javax.persistence.EntityManager;

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
    private TableView<BagProduct> tableview_item;

    @FXML
    private TableColumn<BagProduct, Number> tb_id;

    @FXML
    private TableColumn<BagProduct, String> tb_name;

    @FXML
    private TableColumn<BagProduct, Integer> tb_qty;

    @FXML
    private TableColumn<BagProduct, Double> tb_price;
    
    @FXML
    private TableView<Status> tableview_status;

    @FXML
    private TableColumn<Status, String> tb_date;

    @FXML
    private TableColumn<Status, Integer> tb_status;
    
     @FXML
    protected void initialize() {
        updateTableView();
        
        tb_id.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableview_item.getItems().indexOf(column.getValue()) + 1));
        tb_name.setCellFactory((TableColumn<BagProduct, String> col) ->
            new TableCell<BagProduct, String>() {
                @Override
                public void updateItem(String detail, boolean empty) {
                    super.updateItem(detail, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        BagProduct item = (BagProduct) this.getTableRow().getItem();
                        setText(item.getProductName() + " (" + item.getColorName() + ")");
                    }
                }
            }
        );
        tb_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tb_price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tb_date.setCellValueFactory(new PropertyValueFactory<>("obtainedDate"));
        tb_status.setCellValueFactory(new PropertyValueFactory<>("statusName"));
    }
    
    void updateTableView() {
        tableview_item.setPlaceholder(new Label("No product found!"));
        tableview_status.setPlaceholder(new Label("No status found!"));
    }
    
    void fill(Order od) {
        tf_orderno.setText(od.getId().toString());
        
        ObservableList<BagProduct> dataItem = FXCollections.observableArrayList(od.getProducts());
        ObservableList<Status> dataStatus = FXCollections.observableArrayList(od.getStatus());
        
        tableview_item.setItems(dataItem);
        tableview_status.setItems(dataStatus);
        
        updateTableView();
    }
    
}
