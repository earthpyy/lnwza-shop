package application;

import application.handler.UserHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.controller.Fillable;
import ui.controller.WebViewController;

/**
 *
 * @author SE-lnwza
 */
public class SceneLoader {
    
    private static final String OWNER_HOMEPAGE = "StockView";
    private static final String AGENT_HOMEPAGE = "PurchaseHome";
    
    private static final String CSS_PATH = "/ui/resources/styles/";
    private static final String FXML_PATH = "/ui/fxml/";
    
    private static final ArrayList<String> CSS_FILES = new ArrayList<>(Arrays.asList("bootstrap3.css", "font.css", "table.css"));
    
    private static final int MAIN_WIDTH = 1024;
    private static final int MAIN_HEIGHT = 768;
    private static final int POPUP_WIDTH = 600;
    private static final int POPUP_HEIGHT = 400;
    
    private static Stage main, pop, login, web;
    private static BorderPane mainRoot;
    private static VBox pcRoot;
    private static Scene mainScene, popScene, loginScene, webScene;
    private static Parent mainMenu, mainBody, popBody, loginBody, pcMenu, pcBody, webBody;
    private static FXMLLoader bodyFXML, popFXML, pcFXML, webFXML;
    
    public static void initialize(Stage stage) {
        login = stage;
        main = new Stage();
        pop = new Stage();
        web = new Stage();
        loadLogin();
    }
    
    public static void start() {
        mainRoot = new BorderPane();
        pcRoot = new VBox();
        mainScene = new Scene(mainRoot, MAIN_WIDTH, MAIN_HEIGHT);
    }
    
    public static void loadLogin() {
        start();
        main.close();
        pop.close();
        web.close();
        
        login.setTitle("Login - lnwza SHOP");
        loginBody = load("Login");
        loginScene = new Scene(loginBody, POPUP_WIDTH, POPUP_HEIGHT);
        loadCss(loginScene);
        login.setScene(loginScene);
        login.show();
    }
    
    public static void loadMain() {
        login.close();
        
        main.setTitle("lnwza SHOP");
        mainMenu = load("MenuBar");
        mainRoot.setTop(mainMenu);
        if (UserHandler.getCurrentUser().isOwner()) {
            setBody(OWNER_HOMEPAGE);
        } else {
            loadPurchase();
        }

        loadCss(mainScene);
        main.setScene(mainScene);
        main.show();
    }
    
    public static void loadPurchase() {
        pcMenu = load("PurchaseMenu");
        pcBody = load(AGENT_HOMEPAGE);
        
        pcRoot.getChildren().addAll(pcMenu, pcBody);
        mainRoot.setCenter(pcRoot);
    }
    
    public static void setBody(String name) {
        bodyFXML = loadFXML(name);
        try {
            mainBody = bodyFXML.load();
        } catch (IOException ex) {
        }
        mainRoot.setCenter(mainBody);
    }
    
//    public static void setPCBody(String name) {
//        pcBody = load(name);
//        pcRoot.getChildren().set(1, pcBody);
//        mainRoot.setCenter(pcRoot);
//    }
    
    public static void setPCBody(String name) {
        pcFXML = loadFXML(name);
        try {
            pcBody = pcFXML.load();
        } catch (IOException ex) {
        }
        pcRoot.getChildren().set(1, pcBody);
        mainRoot.setCenter(pcRoot);
    }
    
    public static void popup(String name, String title) {
        popFXML = loadFXML(name);
        try {
            popBody = popFXML.load();
        } catch (IOException ex) {
        }
        popScene = new Scene(popBody, POPUP_WIDTH, POPUP_HEIGHT);
        loadCss(popScene);
        pop.setScene(popScene);
        pop.setTitle(title);
        pop.show();
    }
    
    public static void popup(String name) {
        popup(name, "lnwza SHOP");
    }
    
    public static void popupPay() {
        webFXML = loadFXML("WebView");
        try {
            webBody = webFXML.load();
        } catch (IOException ex) {
        }
        webScene = new Scene(webBody, POPUP_WIDTH, POPUP_HEIGHT);
        loadCss(webScene);
        web.setScene(webScene);
        web.setTitle("Payment - lnwza SHOP");
        web.setOnHiding(event -> {
            enablePC();
        });
        
        web.show();
    }
    
    public static Stage getWebStage() {
        return web;
    }
    
    public static Fillable getBodyController() {
        return bodyFXML.getController();
    }
    
    public static Fillable getPopupController() {
        return popFXML.getController();
    }
    
    public static Fillable getPCController() {
        return pcFXML.getController();
    }
    
    public static WebViewController getWebController() {
        return webFXML.getController();
    }
    
    public static void closePopup() {
        pop.close();
    }
    
    public static void closeWeb() {
        web.close();
    }
    
    public static void disablePC() {
        pcBody.setDisable(true);
    }
    
    public static void enablePC() {
        pcBody.setDisable(false);
    }
    
    private static FXMLLoader loadFXML(String name) {
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(SceneLoader.class.getResource(FXML_PATH + name + ".fxml"));
        } catch (Exception ex) {       
        }
        return fxmlLoader;
    }
    
    private static Parent load(String name) {
//        if (name.equals("OrderView")) {
//            name += Session.getRole();
//        }
        
        Parent parent = null;
        System.out.println("[GUI] Loading " + name + "...");
        try {
            parent = loadFXML(name).load();
        } catch (IOException ex) {
            System.out.println("Cannot load " + name + "!");
            System.exit(0);
        }
        System.out.println("[GUI] " + name + " loaded!");
        return parent;
    }
    
    private static void loadCss(Scene scene) {
        System.out.println("[GUI] Adding CSS...");
        for (String fileName : CSS_FILES) {
            scene.getStylesheets().add(CSS_PATH + fileName);
        }
        System.out.println("[GUI] CSS added!");
    }
    
}