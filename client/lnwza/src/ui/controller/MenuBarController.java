package ui.controller;

import application.Main;
import application.SceneLoader;
import application.Session;
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
    private MenuItem menuStockView;

    @FXML
    private MenuItem menuStockUpdate;

    @FXML
    private MenuItem menuOrderView;

    @FXML
    private MenuItem menuHistoryView;

    @FXML
    private MenuItem menuTransactionView;

    @FXML
    private MenuItem menuAgentView;

    @FXML
    private MenuItem menuLoggedName;

    @FXML
    private MenuItem menuLoggedStatus;

    @FXML
    private MenuItem menuLogOut;
    
    @FXML
    protected void initialize() {
        menuLoggedName.setText(Session.getFullName());
        menuLoggedStatus.setText("Role: " + Session.getRole());
    }
    
    @FXML
    void show(ActionEvent event) {
        String scene = (String) ((MenuItem) event.getSource()).getUserData();
        SceneLoader.setBody(scene);
    }
    
    @FXML
    void showStage(ActionEvent event) {
        String scene = (String) ((MenuItem) event.getSource()).getUserData();
        if (scene.equals("OrderUpdate")) {
            SceneLoader.popup(scene, "Update");
        } else {
            SceneLoader.popup(scene);
        }
    }
    
    @FXML
    void logOut(ActionEvent event) {
        Session.logOut();
        SceneLoader.loadLogin();
    }
}
