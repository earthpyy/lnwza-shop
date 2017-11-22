package ui.controller;

import application.SceneLoader;
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
import application.handler.ProductHandler;
import javafx.scene.control.TableRow;

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
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, ?> tb_checkbox;

    @FXML
    private TableColumn<Product, String> tb_id;

    @FXML
    private TableColumn<Product, ImageView> tb_photo;

    @FXML
    private TableColumn<Product, String> tb_name;

    @FXML
    private TableColumn<Product, String> tb_type;

    @FXML
    private TableColumn<Product, Integer> tb_size;

    @FXML
    private TableColumn<Product, Double> tb_price;
    
    @FXML
    private Label lb_summary;
     
    @FXML
    protected void initialize() {
        tb_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tb_photo.setCellValueFactory(new PropertyValueFactory<>("photoStockView"));
        tb_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tb_type.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        tb_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        tb_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        bt_update.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        
        tableView.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product rowData = tableView.getSelectionModel().getSelectedItem();
                    SceneLoader.popup("StockDetail", "Product #" + rowData.getProductId());

                    StockDetailController ctrl = SceneLoader.getPopupController(StockDetailController.class);
                    ctrl.fill(rowData);
                }
            });
            return row;
        });
        
//        ProductHandler.load();
        ObservableList<Product> data = FXCollections.observableArrayList(ProductHandler.getData());
        tableView.setItems(data);
    }
    
    @FXML
    void update() {
        Product product = tableView.getSelectionModel().getSelectedItem();
        
        SceneLoader.popup("StockUpdate", "Update Product #" + product.getProductId());
        StockUpdateController ctrl = SceneLoader.getPopupController(StockUpdateController.class);
        ctrl.fill(product);
    }
}
