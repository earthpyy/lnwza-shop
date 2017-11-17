package ui.controller;

import application.SceneLoader;
import application.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

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
    private Label loggedName;

    @FXML
    private Label loggedRole;

    @FXML
    private Button bt_logout;
    
    @FXML
    protected void initialize() {
        loggedName.setText(Session.getFullName());
        loggedRole.setText(Session.getRole());
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
