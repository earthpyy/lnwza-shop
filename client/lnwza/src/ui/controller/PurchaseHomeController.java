package ui.controller;

import application.Bag;
import application.SceneLoader;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import application.entity.Product;
import application.handler.ProductHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author SE-lnwza
 */
public class PurchaseHomeController {
    
    @FXML
    private GridPane gridPane;
    
    private static final double IMAGE_HEIGHT = 195;
    private List<Product> products;
    
    @FXML
    protected void initialize() {
        products = ProductHandler.getRecommendData();
        
        int i = 0;
        for (Product product : products) {
            GridPane container = new GridPane();
            
            RowConstraints containerRowA = new RowConstraints();
            RowConstraints containerRowB = new RowConstraints();
            containerRowA.setVgrow(Priority.ALWAYS);
            container.getRowConstraints().add(containerRowA);
            containerRowB.setVgrow(Priority.NEVER);
            container.getRowConstraints().addAll(containerRowB, containerRowB, containerRowB);
            
            ColumnConstraints containerCol = new ColumnConstraints();
            containerCol.setHgrow(Priority.ALWAYS);
            container.getColumnConstraints().add(containerCol);
            
//            Pane imagePane = new Pane();
            ImageView image = product.getPhotoViewByHeight(IMAGE_HEIGHT);
            GridPane.setHalignment(image, HPos.CENTER);
//            imagePane.getChildren().add(image);
//            image.fitHeightProperty().bind(imagePane.heightProperty());
            
            Text txt_name = new Text(product.getName());
            GridPane.setHalignment(txt_name, HPos.CENTER);
            
            Text txt_price = new Text("$" + product.getPrice().toString());
            txt_price.setFill(Color.RED);
            GridPane.setHalignment(txt_price, HPos.CENTER);
            
            Button bt_cart = new Button("Add to cart!");
            GridPane.setHalignment(bt_cart, HPos.CENTER);
            bt_cart.setOnAction((event) -> {
                SceneLoader.popup("PurchaseAdd", product.getName());
                
                PurchaseAddController ctrl = SceneLoader.getPopupController(PurchaseAddController.class);
                ctrl.fill(product);
            });
            
            container.add(image, 0, 0);
            container.add(txt_name, 0, 1);
            container.add(txt_price, 0, 2);
            container.add(bt_cart, 0, 3);
            
            gridPane.add(container, i++, 0);
        }
    }
    
}
