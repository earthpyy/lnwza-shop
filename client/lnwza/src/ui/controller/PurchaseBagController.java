package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;
import application.entity.BagProduct;
import application.Bag;
import application.Delivery;
import application.SceneLoader;
import application.entity.ImageButton;
import application.handler.UserHandler;
import javafx.scene.layout.AnchorPane;

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
    private TextField tf_subtotal;

    @FXML
    private TextField tf_tax;

    @FXML
    private TextField tf_shipping;

    @FXML
    private TextField tf_total;
    
    @FXML
    private AnchorPane anchorPane;
    
    //@FXML
    //private Button bt_checkout;
    
    private Double total;
    
    @FXML
    protected void initialize() {
        ImageButton bt_checkout = new ImageButton("/ui/resources/images/button/ButtonCheckout.png");
        bt_checkout.setOnAction((event) -> {
            checkout();
        });
        anchorPane.getChildren().add(bt_checkout);
        
        tb_photo.setCellValueFactory(new PropertyValueFactory<>("productPhoto"));
        
        tb_detail.setCellFactory((TableColumn<BagProduct, String> col) ->
            new TableCell<BagProduct, String>() {
                @Override
                public void updateItem(String detail, boolean empty) {
                    super.updateItem(detail, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        BagProduct item = getTableView().getItems().get(getIndex());
                        setText(item.getProductName() + " (" + item.getColorName() + ")");
                    }
                }
            }
        );
        
        tb_price.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        tb_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tb_subtotal.setCellValueFactory(new PropertyValueFactory<>("total"));

//        tb_subtotal.setCellFactory((TableColumn<BagProduct, Double> col) ->
//            new TableCell<BagProduct, Double>() {
//                @Override
//                public void updateItem(Double data, boolean empty) {
//                    super.updateItem(data, empty);
//                    if (data == null || empty) {
////                        setText(null);
//                    } else {
////                        BagProduct item = (BagProduct)(this.getTableRow().getItem());
////                        data = item.getProductPrice() * item.getQuantity();
//                        if (getTableRow().getIndex() == 0) {    // TODO: fix this
//                            subTotal.set(subTotal.add(data / 2.0).doubleValue());
//                        } else {
//                            subTotal.set(subTotal.add(data).doubleValue());
//                        }
////                        setText(data.toString());
//                    }
//                }
//            }
//        );
        
//        tf_subtotal.textProperty().bind(Bindings.concat("$", subTotal.asString()));
//        tf_tax.textProperty().bind(Bindings.concat("$", subTotal.multiply(7).divide(100).asString()));
        
        double shippingRate = Delivery.getCost(UserHandler.getCurrentUser().toAgent().getPostCode());
        tf_shipping.setText("$" + shippingRate);
//        tf_total.textProperty().bind(Bindings.concat("$", subTotal.add(subTotal.multiply(7).divide(100)).add(shippingRate)));
        
        bt_checkout.disableProperty().bind(Bindings.size(Bag.getInstance().getItems()).isEqualTo(0));
        
//        ObservableList<BagProduct> data = FXCollections.observableArrayList(Bag.getItems());
        tableView.setItems(Bag.getInstance().getItems());
        
        tf_subtotal.setText(Bag.getInstance().getTotal().toString());
        Double tax = Bag.getInstance().getTotal() * 7 / 100;
        tf_tax.setText(tax.toString());
        total = Bag.getInstance().getTotal() + tax + shippingRate;
        tf_total.setText(total.toString());
    }
    
    
    void checkout() {
        SceneLoader.setPCBody("PurchaseCheckout");
        SceneLoader.getPCController().fill(total);
    }
    
}
