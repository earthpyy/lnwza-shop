
package ui.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

import application.SceneLoader;
import application.entity.Product;
import application.entity.ProductDetail;
import application.entity.Transaction;
import application.entity.TransactionType;
import application.handler.ProductHandler;
import application.handler.TransactionHandler;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;
import org.apache.http.ParseException;

/**
 *
 * @author SE-lnwza
 */
public class StockUpdateController extends Fillable<ProductDetail> {
    @FXML
    private ComboBox<ProductDetail> cb_color;

    @FXML
    private TextField tf_instock;

    @FXML
    private TextField tf_change;

    @FXML
    private TextField tf_update;
    
    @FXML
    private TextField tf_price;
    
    @FXML
    private TextField tf_total;
    
    @FXML
    private Button bt_update;
    
    private Product product;
    private ProductDetail detail;
    private SimpleIntegerProperty changeProperty, inStockProperty;
    private SimpleDoubleProperty priceProperty;
    
    @FXML
    protected void initialize() {
        changeProperty = new SimpleIntegerProperty();
        inStockProperty = new SimpleIntegerProperty();
        priceProperty = new SimpleDoubleProperty();
    }
    
    void fill(Product pd, ProductDetail dt) {
        setProduct(pd);
        setDetail(dt);
        ObservableList<ProductDetail> colorData = FXCollections.observableArrayList(product.getDetail());
        cb_color.setItems(colorData);
        cb_color.getSelectionModel().selectFirst();
        Callback<ListView<ProductDetail>, ListCell<ProductDetail>> factory = lv -> new ListCell<ProductDetail>() {
            @Override
            protected void updateItem(ProductDetail item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getColorName());
                    Rectangle rect = new Rectangle(10, 10);
                    rect.setFill(item.getColor());
                    setGraphic(rect);
                }
            }
        };
        cb_color.setCellFactory(factory);
        cb_color.setButtonCell(factory.call(null));

        try {
        Bindings.bindBidirectional(tf_change.textProperty(), changeProperty, new NumberStringConverter());
        Bindings.bindBidirectional(tf_instock.textProperty(), inStockProperty, new NumberStringConverter());
        Bindings.bindBidirectional(tf_price.textProperty(), priceProperty, new NumberStringConverter());
        } catch (ParseException ex) {
            System.out.println("eiei");
        }
        tf_update.textProperty().bind(changeProperty.add(inStockProperty).asString());
        tf_total.textProperty().bind(priceProperty.multiply(changeProperty).asString());
        
        bt_update.disableProperty().bind(changeProperty.greaterThan(0).and(priceProperty.greaterThan(0).or(priceProperty.isEqualTo(0).and(tf_price.textProperty().isEqualTo("0")))).not());
        
        change();
    }
    
    void fill(Product pd) {
        fill(pd, pd.getDetail().get(0));
    }
    
    @Override
    void fill(ProductDetail dt) {
        fill(dt.getProduct(), dt);
        cb_color.getSelectionModel().select(dt);
    }
    
    void setProduct(Product pd) {
        product = pd;
    }
    
    void setDetail(ProductDetail dt) {
        detail = dt;
    }
    
    @FXML
    void change() {
        setDetail(cb_color.getSelectionModel().getSelectedItem());
        
        Integer inStockValue = detail.getQuantity();
        tf_instock.setText(inStockValue.toString());
    }
    
    @FXML
    void update() {
        detail.setQuantity(changeProperty.get() + inStockProperty.get());
        ProductHandler.updateDetail(detail);
       
        if (!tf_price.getText().equals("0")) {
            Double cost = Double.parseDouble(tf_total.getText());
            cost -= cost * 2;
            Transaction tran = new Transaction(product.getProductId(), TransactionType.STOCK, cost);
            TransactionHandler.add(tran);
        }
        
        SceneLoader.closePopup();
        ProductHandler.load();
    }

}
