package application.payment;

import application.AppProperties;
import application.Bag;
import application.SceneLoader;
import application.entity.PaymentStatus;
import netscape.javascript.JSObject;
import ui.controller.PurchaseCompleteController;
import ui.controller.WebViewController;

/**
 *
 * @author SE-lnwza
 */
public abstract class Payment {
    
    private int shopId;
    private double amount;
    private PaymentStatus status;

    public Payment(double amount) {
        this.shopId = AppProperties.getShopId();
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
        SceneLoader.popupPay();
        
        SceneLoader.getWebController().setURL(url);
        JSObject window = (JSObject) SceneLoader.getWebController().getWebEngine().executeScript("window");
        window.setMember("app", new WebApp());
        
        SceneLoader.disablePC();
    }
    
    protected void complete() {
        if (getStatus() == PaymentStatus.NOTPAY) {
            setStatus(PaymentStatus.PAID);
            Long orderId = Bag.addToOrder();
            Bag.reset();
            SceneLoader.closePopup();
            SceneLoader.setPCBody("PurchaseComplete");
            SceneLoader.enablePC();
        }
    }
    
    protected void returnToBag() {
        SceneLoader.closePopup();
        SceneLoader.setPCBody("PurchaseBag");
        
        SceneLoader.enablePC();
    }
    
    public abstract void pay();
    
    public class WebApp {
        public void success() {
            complete();
        }
        
        public void fail() {
            returnToBag();
        }
    }
    
}