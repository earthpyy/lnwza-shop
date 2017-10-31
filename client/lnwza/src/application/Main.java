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
        System.out.println("Loading Application's properties...");
        AppProperties.load();
        System.out.println("Connecting to database...");
        DatabaseConnection.load();
        
        System.out.println("Loading GUI...");
        MenuBar bar = FXMLLoader.load(getClass().getResource("/ui/fxml/MenuBar.fxml"));
        Parent startPage = FXMLLoader.load(getClass().getResource("/ui/fxml/StockView.fxml"));
        
        root.setTop(bar);
        root.setCenter(startPage);
        
        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("ui/jbootx/bootstrap3.css");
        mainStage.setScene(scene);
        mainStage.setTitle("lnwza SHOP");
        System.out.println("GUI loaded!");
        mainStage.show();
    }
    
    @Override
    public void stop(){
        System.out.println("Closing database connection...");
        DatabaseConnection.getConnection().close();
        System.out.println("Database connected is closed!");
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
