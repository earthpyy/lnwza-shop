package ui.controller;

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
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        tb_photo.setCellValueFactory(new PropertyValueFactory<>("photoView"));
        tb_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tb_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tb_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        tb_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        tableView.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product rowData = row.getItem();
                    Parent pane;
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/fxml/StockDetail.fxml"));
                        pane = fxmlLoader.load();
                        StockDetailController ctrl = (StockDetailController) fxmlLoader.getController();
                        ctrl.fill((Product) tableView.getSelectionModel().getSelectedItem());
                        
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("lnwza SHOP");
                        stage.setScene(new Scene(pane));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
        
        ProductHandler.load();
        ObservableList<Product> data = FXCollections.observableArrayList(ProductHandler.getData());
        tableView.setItems(data);
    }
}
