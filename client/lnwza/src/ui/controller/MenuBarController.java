package ui.controller;

import application.Main;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author SE-lnwza
 */
public class MenuBarController {
    
    @FXML
    private MenuItem menuStockView, menuStockUpdate, menuOrderView, menuHistoryView, menuTransactionView, menuAgentView;
    
    @FXML
    void showStockView(ActionEvent event) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/ui/fxml/StockView.fxml"));
            
            BorderPane border = Main.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void showStockUpdate(ActionEvent event) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/ui/fxml/StockUpdate.fxml"));
            
            BorderPane border = Main.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void showOrderView(ActionEvent event) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/ui/fxml/OrderView.fxml"));
            
            BorderPane border = Main.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void showHistoryView(ActionEvent event) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/ui/fxml/HistoryView.fxml"));
            
            BorderPane border = Main.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void showTransactionView(ActionEvent event) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/ui/fxml/TransactionView.fxml"));
            
            BorderPane border = Main.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void showAgentView(ActionEvent event) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/ui/fxml/AgentView.fxml"));
            
            BorderPane border = Main.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void logOut(ActionEvent event) {
        
    }
}
