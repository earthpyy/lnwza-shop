
package ui.controller;

import application.SceneLoader;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import application.entity.Product;
import application.entity.ProductDetail;

/**
 *
 * @author SE-lnwza
 */
public class StockDetailController {
    
    @FXML
    private ScrollPane scroll;
    
    @FXML
    private ImageView im_product;

    @FXML
    private Label lb_id;

    @FXML
    private Label lb_name;

    @FXML
    private Label lb_price;

    @FXML
    private Label lb_size;

    @FXML
    private Label lb_type;

    @FXML
    private Label lb_description;

    @FXML
    private TableView<ProductDetail> tableView;

    @FXML
    private TableColumn<ProductDetail, Number> tb_no;

    @FXML
    private TableColumn<ProductDetail, String> tb_color;

    @FXML
    private TableColumn<ProductDetail, Integer> tb_qty;

    @FXML
    private Button bt_update;

    private Product product;
    
    @FXML
    protected void initialize() {
        updateTableView();
        
        tb_no.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(column.getValue()) + 1));
        tb_color.setCellValueFactory(new PropertyValueFactory<>("colorName"));
        tb_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        bt_update.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
    }
    
    // TODO: format this table
    void updateTableView() {
        tableView.setPlaceholder(new Label("No product found!"));
//        tableView.setFixedCellSize(25);
//        if (tableView.getItems().isEmpty()) {
//            tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().add(26));
//        } else {
//            tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(1.01)));
//        }
//        tableView.minHeightProperty().bind(tableView.prefHeightProperty());
//        tableView.maxHeightProperty().bind(tableView.prefHeightProperty());
    }
    
    void fill(Product pd) {
        set(pd);
        im_product.setImage(product.getPhoto());
        lb_id.setText(product.getProductId());
        lb_name.setText(product.getName());
        lb_price.setText(product.getPrice().toString());
        lb_size.setText(product.getSize());
        lb_type.setText(product.getType().getName());
        lb_description.setText(product.getDescription());

        ObservableList<ProductDetail> data = FXCollections.observableArrayList(product.getDetail());
        tableView.setItems(data);
        
        updateTableView();
    }
    
    void set(Product pd) {
        product = pd;
    }
    
    @FXML
    void update() {
        ProductDetail detail = tableView.getSelectionModel().getSelectedItem();
        
        SceneLoader.popup("StockUpdate", "Update Product #" + product.getProductId());
        StockUpdateController ctrl = SceneLoader.getPopupController(StockUpdateController.class);
        ctrl.fill(detail);
    }
}
