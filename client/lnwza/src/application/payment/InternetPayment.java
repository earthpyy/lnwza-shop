package application.payment;

import application.payment.Payment;

/**
 *
 * @author SE-lnwza
 */
public class InternetPayment extends Payment {
    
    private static final String payURL = "https://lnwza.earthpyy.com/bank/internet.php";

    public InternetPayment(double amount) {
        super(amount);
    }
    
    public void pay() {
        open(payURL);
    }
    
}
