package ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import application.entity.BagProduct;
import application.Bag;
import javafx.scene.control.TableCell;

/**
 *
 * @author SE-lnwza
 */
public class PurchaseBagController {
    
    @FXML
    private TableView<BagProduct> tableView;
    
    @FXML
    private TableColumn<BagProduct, ImageView> tb_photo;

    @FXML
    private TableColumn<BagProduct, String> tb_detail;

    @FXML
    private TableColumn<BagProduct, Double> tb_price;

    @FXML
    private TableColumn<BagProduct, Integer> tb_qty;

    @FXML
    private TableColumn<BagProduct, Double> tb_subtotal;

    @FXML
    private TableColumn<BagProduct, ?> tb_remove;

    @FXML
    private TextField tf_subtotal;

    @FXML
    private TextField tf_tax;

    @FXML
    private TextField tf_shipping;

    @FXML
    private TextField tf_total;
    
    @FXML
    protected void initialize() {
        tb_photo.setCellValueFactory(new PropertyValueFactory<>("productPhoto"));
        
        tb_detail.setCellFactory((TableColumn<BagProduct, String> col) ->
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
        
        tb_price.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        tb_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        tb_subtotal.setCellFactory((TableColumn<BagProduct, Double> col) ->
            new TableCell<BagProduct, Double>() {
                @Override
                public void updateItem(Double subTotal, boolean empty) {
                    super.updateItem(subTotal, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        BagProduct item = (BagProduct) this.getTableRow().getItem();
                        subTotal = item.getProductPrice() * item.getQuantity();
                        setText(subTotal.toString());
                    }
                }
            }
        );
        
        ObservableList<BagProduct> data = FXCollections.observableArrayList(Bag.getItems());
        tableView.setItems(data);
    }
    
}
