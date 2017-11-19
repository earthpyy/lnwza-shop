package application;

/**
 *
 * @author SE-lnwza
 */
public class InternetPayment extends Payment {
    
    private static final String payURL = "https://lnwzabank.earthpyy.com/";

    public InternetPayment(int shopId, double amount) {
        super(shopId, amount);
    }
    
    public void pay() {
        open(payURL);
    }
    
}
