package application.payment;

import application.AppProperties;
import application.Bag;
import application.SceneLoader;
import application.entity.PaymentStatus;
import application.handler.OrderHandler;
import netscape.javascript.JSObject;

/**
 *
 * @author SE-lnwza
 */
public abstract class Payment {
    
    protected Integer shopId;
    protected Bag bag;
    protected Double amount;
    protected PaymentStatus status;

    public Payment(Bag bag, Double amount) {
        this.shopId = AppProperties.getShopId();
        this.bag = bag;
        this.amount = amount;
        this.status = PaymentStatus.NOTPAY;
    }

    public Integer getShopId() {
        return shopId;
    }

    public Bag getBag() {
        return bag;
    }

    public Double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
    
    public void setNextStatus() {
        status = status.getNext();
    }
    
    protected void open(String url) {
        SceneLoader.popupPay();
        SceneLoader.getWebController().setURL(url);
        JSObject window = (JSObject) SceneLoader.getWebController().getWebEngine().executeScript("window");
        window.setMember("app", new WebApp());
        
        SceneLoader.disablePC();
    }
    
    protected void complete() {
        if (status == PaymentStatus.WAITING) {
            status = PaymentStatus.PAID;
            OrderHandler.create(this);
            Bag.getInstance().reset();
            SceneLoader.closePopup();
            SceneLoader.setPCBody("PurchaseComplete");
            SceneLoader.enablePC();
        }
        SceneLoader.closeWeb();
    }
    
    protected void returnToBag() {
        status = PaymentStatus.NOTPAY;
        SceneLoader.closeWeb();
        SceneLoader.setPCBody("PurchaseBag");
        
        SceneLoader.enablePC();
    }
    
    public abstract String genURL(int shopId, double amount);
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
