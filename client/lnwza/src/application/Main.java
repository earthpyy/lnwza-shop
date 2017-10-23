/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author EARTHPYY
 */
public class Main extends Application {
    
    private static BorderPane root = new BorderPane();
    
    @Override
    public void start(Stage mainStage) throws Exception {
        MenuBar menubar = FXMLLoader.load(getClass().getResource("/ui/fxml/MenuBar.fxml"));
        Parent page1 = FXMLLoader.load(getClass().getResource("/ui/fxml/StockView.fxml"));
        
        root.setTop(menubar);
        root.setCenter(page1);
        
        Scene scene = new Scene(root, 1024, 768);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
    public static BorderPane getRoot() {
        return root;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
