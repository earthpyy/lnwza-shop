
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author SE-lnwza
 */
public class OrderDetailController extends Fillable<Order> {
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
    private SimpleIntegerProperty rowSizeProperty, rowStatusSizeProperty;
    
     @FXML
    protected void initialize() {
        rowSizeProperty = new SimpleIntegerProperty(0);
        rowStatusSizeProperty = new SimpleIntegerProperty(0);
        
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
        
        tableview_item.setPlaceholder(new Label("No product found!"));
        tableview_item.setFixedCellSize(30);
        tableview_item.prefHeightProperty().bind(tableview_item.fixedCellSizeProperty().multiply(rowSizeProperty.add(1)));
        tableview_item.minHeightProperty().bind(tableview_item.prefHeightProperty());
        tableview_item.maxHeightProperty().bind(tableview_item.prefHeightProperty());
        
        tableview_item.itemsProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
//                rowSizeProperty.bind(Bindings.size(tableView.getItems()));
                rowSizeProperty.set(tableview_item.getItems().size());
            }
        });
        
        tableview_status.setPlaceholder(new Label("No status found!"));
        tableview_status.setFixedCellSize(30);
        tableview_status.prefHeightProperty().bind(tableview_status.fixedCellSizeProperty().multiply(rowStatusSizeProperty.add(1)));
        tableview_status.minHeightProperty().bind(tableview_status.prefHeightProperty());
        tableview_status.maxHeightProperty().bind(tableview_status.prefHeightProperty());
        
        tableview_status.itemsProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
//                rowSizeProperty.bind(Bindings.size(tableView.getItems()));
                rowStatusSizeProperty.set(tableview_status.getItems().size());
            }
        });
    }
    
    @FXML
    void close() {
        SceneLoader.closePopup();
    }
    
    @FXML
    void update() {
        SceneLoader.popup("OrderUpdate", "Update Order #" + order.getId() + " - lnwza SHOP");
        SceneLoader.getPopupController().fill(order);
    }
    
    @Override
    void fill(Order od) {
        set(od);
        tf_orderno.setText(order.getId().toString());
        tf_trackno.setText(order.getTrackNo());
        
        ObservableList<BagProduct> dataItem = FXCollections.observableArrayList(order.getProducts());
        ObservableList<Status> dataStatus = FXCollections.observableArrayList(order.getStatus());
        
        tableview_item.setItems(dataItem);
        tableview_status.setItems(dataStatus);
        
        bt_update.setDisable(order.getLastStatus() != OrderStatus.PREPARING);

    }
    
    void set(Order od) {
        order = od;
    }
    
}
