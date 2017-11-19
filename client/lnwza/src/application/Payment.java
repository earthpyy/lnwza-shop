package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import netscape.javascript.JSObject;
import ui.controller.WebViewController;

/**
 *
 * @author SE-lnwza
 */
public abstract class Payment {
    
    public static final int SHOP_ID = 1;
    
    private int shopId;
    private double amount;

    public Payment(int shopId, double amount) {
        this.shopId = shopId;
        this.amount = amount;
    }

    public int getShopId() {
        return shopId;
    }

    public double getAmount() {
        return amount;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    // TODO: add parameter to web
    public void open(String url) {
        SceneLoader.popup("WebView");
        
        WebViewController ctrl = SceneLoader.getPopupController(WebViewController.class);
        ctrl.setURL(url);
//        ctrl.getWebEngine().getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
//            if (newState == State.SUCCEEDED) {
//                JSObject win = (JSObject) ctrl.getWebEngine().executeScript("window");
//                win.setMember("app", new WebApp());
//            }
//        });
        
        JSObject window = (JSObject)ctrl.getWebEngine().executeScript("window");
        window.setMember("app", new WebApp());
    }
    
    public abstract void pay();
    
    public class WebApp {
        public void parseResult(boolean result) {
            System.out.println(result);
        }
    }
    
}
