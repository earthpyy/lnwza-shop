
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
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableCell;

import application.entity.Order;
import application.entity.OrderStatus;
import application.entity.Status;
import application.SceneLoader;
import application.entity.BagProduct;
import application.handler.UserHandler;

/**
 *
 * @author SE-lnwza
 */
public class OrderDetailController {
    @FXML
    private TextField tf_orderno;

    @FXML
    private TextField tf_trackno;

    @FXML
    private Button bt_update;

    @FXML
    private Button bt_cancel;
    
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
    private TableColumn<Status, String> tb_time;

    @FXML
    private TableColumn<Status, Integer> tb_status;
    
    private Order order;
    
     @FXML
    protected void initialize() {
        updateTableView();
        
        if (!UserHandler.getCurrentUser().isOwner()) {
            bt_update.setVisible(false);
        }
        
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
        tb_time.setCellValueFactory(new PropertyValueFactory<>("obtainedTime"));
        tb_status.setCellValueFactory(new PropertyValueFactory<>("statusName"));
    }
    
    @FXML
    void close() {
        SceneLoader.closePopup();
    }
    
    @FXML
    void update() {
        SceneLoader.popup("OrderUpdate", "Update Order #" + order.getId());
        OrderUpdateController ctrl = SceneLoader.getPopupController(OrderUpdateController.class);
        ctrl.fill(order);
    }
    
    void updateTableView() {
        // TODO: format this
        tableview_item.setPlaceholder(new Label("No product found!"));
        tableview_status.setPlaceholder(new Label("No status found!"));
    }
    
    void fill(Order od) {
        set(od);
        tf_orderno.setText(order.getId().toString());
        tf_trackno.setText(order.getTrackNo());
        
        ObservableList<BagProduct> dataItem = FXCollections.observableArrayList(order.getProducts());
        ObservableList<Status> dataStatus = FXCollections.observableArrayList(order.getStatus());
        
        tableview_item.setItems(dataItem);
        tableview_status.setItems(dataStatus);
        
        bt_update.setDisable(order.getLastStatus().getStatus() != OrderStatus.PREPARING);
        
        updateTableView();
    }
    
    void set(Order od) {
        order = od;
    }
    
}
