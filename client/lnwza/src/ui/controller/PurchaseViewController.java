package ui.controller;
import application.SceneLoader;
import application.entity.ImageButton;
import application.entity.Product;
import application.entity.ProductType;
import application.handler.ProductHandler;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
/**
 *
 * @author SE-lnwza
 */
public class PurchaseViewController {
    
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private GridPane gridPane;
    
    @FXML
    private ColumnConstraints colCon;

    private static final double ROW_HEIGHT = 330;
    private static final double IMAGE_HEIGHT = 250;
    private ArrayList<Product> products;
    
    @FXML
    protected void initialize() {
        // TODO: move it to fxml
        gridPane.getColumnConstraints().addAll(colCon, colCon);
    }
    
    void fill(ProductType type) {
        products = ProductHandler.getDataFromType(type);
        int i = 0;
        for (Product product : products) {
            if (i % 3 == 0) {
                RowConstraints rowCon = new RowConstraints();
                rowCon.setValignment(VPos.CENTER);
                rowCon.setPrefHeight(ROW_HEIGHT);
                gridPane.getRowConstraints().add(rowCon);
            }
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
            
            ImageView image = product.getPhotoViewByHeight(IMAGE_HEIGHT);
            GridPane.setHalignment(image, HPos.CENTER);
            
            Text txt_name = new Text(product.getName());
            GridPane.setHalignment(txt_name, HPos.CENTER);
            
            Text txt_price = new Text("฿" + product.getPrice().toString());
            txt_price.setFill(Color.RED);
            GridPane.setHalignment(txt_price, HPos.CENTER);
            
            ImageButton bt_cart = new ImageButton("/ui/resources/images/button/ButtonAdd.png");
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
            
            gridPane.add(container, i % 3, i / 3);
            i++;
        }
    }
    
}
