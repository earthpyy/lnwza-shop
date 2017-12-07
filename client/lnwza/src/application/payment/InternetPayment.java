package application.payment;

import application.Bag;
import application.entity.PaymentStatus;

/**
 *
 * @author SE-lnwza
 */
public class InternetPayment extends Payment {
    
    private static final String payURL = "https://lnwza.earthpyy.com/bank/internet.php";

    public InternetPayment(Bag bag, double amount) {
        super(bag, amount);
    }
    
    @Override
    public void pay() {
        open(genURL(getShopId(), amount));
        status = PaymentStatus.WAITING;
    }
    
    @Override
    public String genURL(int shopId, double amount) {
        return payURL + "?shopId=" + shopId + "&amount=" + amount;
    }
    
}
