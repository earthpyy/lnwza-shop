
package ui.controller;

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
    
//    TableColumn<Person, Number> indexColumn = new TableColumn<Person, Number>("#");
//indexColumn.setSortable(false);
//indexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(YourTable.getItems().indexOf(column.getValue())));
    
    @FXML
    protected void initialize() {
        updateTableView();
        
        tb_no.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(column.getValue()) + 1));
        tb_color.setCellValueFactory(new PropertyValueFactory<>("colorName"));
        tb_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
    
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
        im_product.setImage(pd.getPhoto());
        lb_id.setText(pd.getProductId());
        lb_name.setText(pd.getName());
        lb_price.setText(pd.getPrice().toString());
        lb_size.setText(pd.getSize());
        lb_type.setText(pd.getType());
        lb_description.setText(pd.getDescription());
        
//        ProductDetailHandler.load(pd.getDetail());
//        ObservableList<ProductDetail> data = FXCollections.observableArrayList(ProductDetailHandler.getData());
        ObservableList<ProductDetail> data = FXCollections.observableArrayList(pd.getDetail());
        tableView.setItems(data);
        
        updateTableView();
    }
}
