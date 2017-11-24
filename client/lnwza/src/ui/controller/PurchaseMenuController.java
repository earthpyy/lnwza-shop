package ui.controller;

import application.Bag;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import application.SceneLoader;
import application.entity.ProductType;
import application.handler.ProductTypeHandler;
import javafx.beans.binding.Bindings;

/**
 *
 * @author SE-lnwza
 */
public class PurchaseMenuController {
    
    @FXML
    private MenuBar menuBar;
    
    @FXML
    private Menu menuPCRecommend;

    @FXML
    private Menu menuPCBag;
    
    @FXML
    protected void initialize() {
        ArrayList<ProductType> types = ProductTypeHandler.getData();
        for (ProductType type : types) {
            Menu menu = new Menu(type.getName());
            MenuItem menuItem = new MenuItem("");
            menuItem.setOnAction((event) -> {
                showCategory(type);
            });
            menu.showingProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue) {
                    menu.getItems().get(0).fire();
                }
            });
            
            menu.getItems().add(menuItem);
            menuBar.getMenus().add(menu);
        }
        
        menuPCRecommend.showingProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue) {
                menuPCRecommend.getItems().get(0).fire();
            }
        });
        menuPCBag.showingProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue) {
                menuPCBag.getItems().get(0).fire();
            }
        });
        
        menuPCBag.textProperty().bind(Bindings.concat("Bag (", Bag.getAmountProperty().asString(), ")"));
        
    }

    @FXML
    void showStore(ActionEvent event) {
        String scene = (String) ((MenuItem) event.getSource()).getUserData();
        SceneLoader.setPCBody(scene);
    }
    
    void showCategory(ProductType type) {
        SceneLoader.setPCBody("PurchaseView");

        PurchaseViewController ctrl = SceneLoader.getPCController(PurchaseViewController.class);
        ctrl.fill(type);
    }
    
}
