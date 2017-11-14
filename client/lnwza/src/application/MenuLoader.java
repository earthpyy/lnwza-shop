package application;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author SE-lnwza
 */
public class MenuLoader {
    
    private static final String HOMEPAGE = "StockView";
    
    private static final String CSS_PATH = "/ui/resources/";
    private static final String FXML_PATH = "/ui/fxml/";
    private static final int MAIN_WIDTH = 1024;
    private static final int MAIN_HEIGHT = 768;
    private static final int POPUP_WIDTH = 600;
    private static final int POPUP_HEIGHT = 400;
    
    private static Stage main, pop;
    private static BorderPane root;
    private static Scene scene, popScene;
    private static MenuBar menu;
    private static Parent body, popBody;
    
    public static void initialize(Stage stage) {
        main = stage;
        main.setTitle("lnwza SHOP");
        pop = new Stage();
        pop.hide();
        root = new BorderPane();
        
        menu = (MenuBar) load("MenuBar");
        root.setTop(menu);
        setBody(HOMEPAGE);

        scene = new Scene(root, MAIN_WIDTH, MAIN_HEIGHT);
        loadCss(scene);
        main.setScene(scene);
        main.show();
    }
    
    public static void setBody(String name) {
        body = load(name);
        root.setCenter(body);
    }
    
    public static void popup(String name, String title) {
        popBody = load(name);
        popScene = new Scene(popBody, POPUP_WIDTH, POPUP_HEIGHT);
        loadCss(popScene);
        pop.setScene(popScene);
        pop.setTitle(title);
        pop.show();
    }
    
    public static void popup(String name) {
        popup(name, "lnwza SHOP");
    }
    
    private static Parent load(String name) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(MenuLoader.class.getResource(FXML_PATH + name + ".fxml"));
        } catch (IOException e) {
        }
        return parent;
    }
    
    private static void loadCss(Scene scene) {
        for (String fileName : getCssFileName()) {
            scene.getStylesheets().add(CSS_PATH + fileName);
        }
    }
    
    private static ArrayList<String> getCssFileName() {
        ArrayList<String> result = new ArrayList<>();
        File folder = new File("src/" + CSS_PATH);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".css")) {
                result.add(file.getName());
            }
        }
        return result;
    }
    
}
