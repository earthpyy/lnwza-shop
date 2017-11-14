package ui.controller;

import application.Main;
import application.MenuLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author SE-lnwza
 */
public class MenuBarController {
    
    @FXML
    private MenuItem menuStockView, menuStockUpdate, menuOrderView, menuHistoryView, menuTransactionView, menuAgentView;
    
    @FXML
    void show(ActionEvent event) {
        String scene = (String) ((MenuItem) event.getSource()).getUserData();
        MenuLoader.setBody(scene);
    }
    
    @FXML
    void showStage(ActionEvent event) {
        String scene = (String) ((MenuItem) event.getSource()).getUserData();
        if (scene.equals("OrderUpdate")) {
            MenuLoader.popup(scene, "Update");
        } else {
            MenuLoader.popup(scene);
        }
    }
    
    @FXML
    void logOut(ActionEvent event) {
        
    }
}
