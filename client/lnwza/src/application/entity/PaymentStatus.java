package application.entity;

/**
 *
 * @author SE-lnwza
 */
public enum PaymentStatus {
    
    // normal
    NOTPAY,
    WAITING,
    PAID,
    // error
    ERROR;
    
    public String getName() {
        switch (this) {
            case NOTPAY:
                return "Not paid yet";
            case WAITING:
                return "Waiting for confirm";
            case PAID:
                return "Paid";
            default:
                return "Error";
        }
    }
    
    public PaymentStatus getNext() {
        switch (this) {
            case NOTPAY:
                return WAITING;
            case WAITING:
                return PAID;
            default:
                return ERROR;
        }
    }

}