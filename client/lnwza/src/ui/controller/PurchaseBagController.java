package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import application.entity.BagProduct;
import application.Bag;
import application.SceneLoader;
import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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
    private Button bt_checkout;
    
    private DoubleProperty subTotal;
    
    @FXML
    protected void initialize() {
        subTotal = new SimpleDoubleProperty(0);
        
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
                public void updateItem(Double data, boolean empty) {
                    super.updateItem(data, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        BagProduct item = (BagProduct) this.getTableRow().getItem();
                        data = item.getProductPrice() * item.getQuantity();
                        if (getTableRow().getIndex() == 0) {    // TODO: fix this
                            subTotal.set(subTotal.add(data / 2.0).doubleValue());
                        } else {
                            subTotal.set(subTotal.add(data).doubleValue());
                        }
                        setText(data.toString());
                    }
                }
            }
        );
        
        tf_subtotal.textProperty().bind(Bindings.concat("฿", subTotal.asString()));
        tf_tax.textProperty().bind(Bindings.concat("฿", subTotal.multiply(7).divide(100).asString()));
        // TODO: connect with delivery system
        tf_shipping.setText("฿2.0");
        // TODO: update shipping rate
        tf_total.textProperty().bind(Bindings.concat("฿", subTotal.add(subTotal.multiply(7).divide(100)).add(2)));
        
//        ObservableList<BagProduct> data = FXCollections.observableArrayList(Bag.getItems());
        tableView.setItems(Bag.getItems());
    }
    
    @FXML
    void checkout(ActionEvent event) {
        SceneLoader.setPCBodyWithLoadFXML("PurchaseCheckout");
        
        PurchaseCheckoutController ctrl = SceneLoader.getPCController(PurchaseCheckoutController.class);
        ctrl.fill(Bag.getAmount(), Double.parseDouble(tf_total.getText().substring(1)));
    }
    
}
