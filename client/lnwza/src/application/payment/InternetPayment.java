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
    
    public void pay() {
        open(payURL + "?shopId=" + getShopId() + "&amount=" + getAmount());
        status = PaymentStatus.WAITING;
    }
    
}
