package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author SE-lnwza
 */
public class Main extends Application {
    
    @Override
    public void start(Stage mainStage) throws Exception {
        System.out.println("Loading Application's properties...");
        AppProperties.load();
        System.out.println("Initialize Date...");
        MyDate.initialize();
        System.out.println("Connecting to database...");
        DatabaseConnection.load();
        
        System.out.println("Loading GUI...");
        MenuLoader.initialize(mainStage);
        System.out.println("GUI loaded!");
    }
    
    @Override
    public void stop(){
        System.out.println("Closing database connection...");
        DatabaseConnection.getConnection().close();
        System.out.println("Database connected is closed!");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
