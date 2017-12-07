package application.payment;

import application.Bag;
import application.entity.PaymentStatus;

/**
 *
 * @author SE-lnwza
 */
public class CreditPayment extends Payment {
    
    private static final String payURL = "https://lnwza.earthpyy.com/bank/creditcard.php";

    public CreditPayment(Bag bag, double amount) {
        super(bag, amount);
    }
    
    @Override
    public void pay() {
        open(payURL + "?shopId=" + getShopId() + "&amount=" + getAmount());
        status = PaymentStatus.WAITING;
    }
    
}