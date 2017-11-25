package application.entity;

/**
 *
 * @author SE-lnwza
 */
public class CreditPayment extends Payment {
    
    private static final String payURL = "https://lnwza.earthpyy.com/bank/test.php";

    public CreditPayment(double amount) {
        super(amount);
    }
    
    public void pay() {
        open(payURL + "?shopId=" + getShopId() + "&amount=" + getAmount());
    }
    
}