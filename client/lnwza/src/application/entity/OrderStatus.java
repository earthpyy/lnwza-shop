package application.entity;

/**
 *
 * @author SE-lnwza
 */
public enum OrderStatus {
    
    // normal
    NOTPAY,
    WAITING,
    PAID,
    PREPARING,
    DELIVERING,
    RECEIVED,
    // abnormal
    RETURN,
    CANCELLED,
    // error
    ERROR;
    
    public static OrderStatus getNext(OrderStatus status) {
        switch (status) {
            case NOTPAY:
                return WAITING;
            case WAITING:
                return PAID;
            case PAID:
                return PREPARING;
            case PREPARING:
                return DELIVERING;
            case DELIVERING:
                return RECEIVED;
            default:
                return ERROR;
        }
    }
}
