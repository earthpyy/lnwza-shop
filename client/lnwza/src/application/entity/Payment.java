package application.entity;

import application.Bag;
import application.SceneLoader;
import netscape.javascript.JSObject;
import ui.controller.WebViewController;

/**
 *
 * @author SE-lnwza
 */
public abstract class Payment {
    // TODO: move to AppProperties
    public static final int SHOP_ID = 1;
    
    private int shopId;
    private double amount;
    private PaymentStatus status;

    public Payment(int shopId, double amount) {
        this.shopId = shopId;
        this.amount = amount;
        this.status = PaymentStatus.NOTPAY;
    }

    public int getShopId() {
        return shopId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
    
    public void setNextStatus() {
        status = status.getNext();
    }
    
    // TODO: add parameter to web
    protected void open(String url) {
        SceneLoader.popup("WebView");
        
        WebViewController ctrl = SceneLoader.getPopupController(WebViewController.class);
        ctrl.setURL(url);
//        ctrl.getWebEngine().getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
//            if (newState == State.SUCCEEDED) {
//                JSObject win = (JSObject) ctrl.getWebEngine().executeScript("window");
//                win.setMember("app", new WebApp());
//            }
//        });
        
        JSObject window = (JSObject) ctrl.getWebEngine().executeScript("window");
        window.setMember("app", new WebApp());
    }
    
    public abstract void pay();
    
    public class WebApp {
        public void success() {
            if (getStatus() == PaymentStatus.NOTPAY) {
                setStatus(PaymentStatus.PAID);
                Bag.addToOrder();
            }
        }
    }
    
}
