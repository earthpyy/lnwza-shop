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
 * @author SE-lnwza
 */
public class Main extends Application {
    
    private static final BorderPane root = new BorderPane();
    
    @Override
    public void start(Stage mainStage) throws Exception {
        AppProperties.load();
        DatabaseConnection.load();
        
        MenuBar bar = FXMLLoader.load(getClass().getResource("/ui/fxml/MenuBar.fxml"));
        Parent startPage = FXMLLoader.load(getClass().getResource("/ui/fxml/StockView.fxml"));
        
        root.setTop(bar);
        root.setCenter(startPage);
        
        Scene scene = new Scene(root, 1024, 768);
        mainStage.setScene(scene);
        mainStage.show();
        
        DatabaseConnection.getConnection().close();
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
