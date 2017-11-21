package application.entity;

/**
 *
 * @author SE-lnwza
 */
public enum OrderStatus {
    
    // normal
    PREPARING,
    DELIVERING,
    RECEIVED,
    // abnormal
    RETURN,
    CANCELLED,
    // error
    ERROR;
    
    public String getName() {
        switch (this) {
            case PREPARING:
                return "Preparing order";
            case DELIVERING:
                return "Package delivering";
            case RECEIVED:
                return "Package received";
            case RETURN:
                return "Returning to shop";
            case CANCELLED:
                return "Cancelled";
            default:
                return "Error";
        }
    }
    
    public OrderStatus getNext() {
        switch (this) {
            case PREPARING:
                return DELIVERING;
            case DELIVERING:
                return RECEIVED;
            default:
                return ERROR;
        }
    }
}
