
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
import application.handler.ProductHandler;

/**
 *
 * @author SE-lnwza
 */
public class StockUpdateController {
    @FXML
    private ComboBox<ProductDetail> cb_color;

    @FXML
    private TextField tf_instock;

    @FXML
    private TextField tf_change;

    @FXML
    private TextField tf_update;
    
    private Product product;
    private ProductDetail detail;
    private SimpleIntegerProperty changeProperty, inStockProperty;
    
    @FXML
    protected void initialize() {
        changeProperty = new SimpleIntegerProperty();
        inStockProperty = new SimpleIntegerProperty();
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

        Bindings.bindBidirectional(tf_change.textProperty(), changeProperty, new NumberStringConverter());
        Bindings.bindBidirectional(tf_instock.textProperty(), inStockProperty, new NumberStringConverter());
        tf_update.textProperty().bind(changeProperty.add(inStockProperty).asString());
    }
    
    void fill(Product pd) {
        fill(pd, pd.getDetail().get(0));
    }
    
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
        SceneLoader.closePopup();
    }

}
