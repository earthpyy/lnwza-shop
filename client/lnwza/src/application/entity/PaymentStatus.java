package application.entity;

/**
 *
 * @author SE-lnwza
 */
public enum PaymentStatus {
    
    // normal
    NOTPAY,
    PAID,
    // error
    ERROR;
    
    public String getName() {
        switch (this) {
            case NOTPAY:
                return "Not paid yet";
            case PAID:
                return "Paid";
            default:
                return "Error";
        }
    }
    
    public PaymentStatus getNext() {
        switch (this) {
            case NOTPAY:
                return PAID;
            default:
                return ERROR;
        }
    }
    
    public static PaymentStatus parseStatus(String value) {
        if (value.equals("NOTPAY")) {
            return NOTPAY;
        } else if (value.equals("PAID")) {
            return PAID;
        } else {
            return ERROR;
        }
    }
}

