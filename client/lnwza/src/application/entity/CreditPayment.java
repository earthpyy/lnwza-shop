package application.entity;

import application.entity.Payment;

/**
 *
 * @author SE-lnwza
 */
public class CreditPayment extends Payment {
    
    private static final String payURL = "https://lnwzabank.earthpyy.com/test.php";

    public CreditPayment(int shopId, double amount) {
        super(shopId, amount);
    }
    
    public void pay() {
        open(payURL + "?shopId=" + getShopId() + "&amount=" + getAmount());
    }
    
}