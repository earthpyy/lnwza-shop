package ui.controller;

import application.Bag;
import application.SceneLoader;
import application.handler.UserHandler;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 *
 * @author SE-lnwza
 */
public class MenuBarController {
    
    @FXML
    private Menu menuStock;

    @FXML
    private MenuItem menuStockView;

    @FXML
    private MenuItem menuStockUpdate;

    @FXML
    private Menu menuStore;

    @FXML
    private MenuItem menuStoreHome;

    @FXML
    private MenuItem menuStoreBag;

    @FXML
    private Menu menuOrder;

    @FXML
    private MenuItem menuOrderView;

    @FXML
    private Menu menuHistory;

    @FXML
    private MenuItem menuHistoryView;

    @FXML
    private Menu menuTransactions;

    @FXML
    private MenuItem menuTransactionView;

    @FXML
    private Menu menuAgents;

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
        loggedName.setText(UserHandler.getCurrentUser().getName());
        loggedRole.setText(UserHandler.getCurrentUser().getRole().toUpperCase());
        if (UserHandler.getCurrentUser().isOwner()) {
            loggedRole.setStyle("-fx-text-fill: red;");
            
            menuStore.setVisible(false);
        } else {
            loggedRole.setStyle("-fx-text-fill: green;");
            
            menuStock.setVisible(false);
            menuHistory.setVisible(false);
            menuTransactions.setVisible(false);
            menuAgents.setVisible(false);

            menuStoreBag.textProperty().bind(Bindings.concat("Bag (", Bag.getInstance().getAmountProperty().asString(), ")"));
        }
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
    void showStore(ActionEvent event) {
        String scene = (String) ((MenuItem) event.getSource()).getUserData();
        SceneLoader.setPCBody(scene);
    }
    
    @FXML
    void logOut(ActionEvent event) {
        UserHandler.logOut();
        SceneLoader.loadLogin();
    }
}
