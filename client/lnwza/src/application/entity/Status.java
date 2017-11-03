package application.entity;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author SE-lnwza
 */
@Entity
public class Status {
    
    public static final int ERROR = 0;
    public static final int NOTPAY = 1;
    public static final int PAID = 2;
    public static final int PREPARING = 3;
    public static final int DELIVERING = 4;
    public static final int RECEIVED = 5;
    public static final int RETURN = 8;
    public static final int CANCELLED = 9;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Order order;
    private Integer status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date obtainedDate;
    
    public Status(Order order, Integer status) {
        this.order = order;
        this.status = status;
        this.obtainedDate = new Date();
    }
        
    public Status(Order order) {
        this(order, NOTPAY);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Order getOrder() {
        return order;
    }

    public Integer getStatus() {
        return status;
    }
    
    public String getStatusName() {
        String word;
        switch (status) {
            case NOTPAY:
                word = "Not paid yet"; break;
            case PAID:
                word = "Paid"; break;
            case PREPARING:
                word = "Preparing order"; break;
            case DELIVERING:
                word = "Package delivering"; break;
            case RECEIVED:
                word = "Package received"; break;
            case RETURN:
                word = "Returning to shop"; break;
            case CANCELLED:
                word = "Cancelled"; break;
            default:
                word = "Error"; break;
        }
//        if (status == NOTPAY) {
//            word = "Not paid yet";
//        } else if (status == PAID) {
//            word = "Paid";
//        } else if (status == PREPARING) {
//            word = "Preparing order";
//        } else if (status == DELIVERING) {
//            word = "Package delivering";
//        } else if (status == RECEIVED) {
//            word = "Package received";
//        } else if (status == RETURN) {
//            word = "Returning to shop";
//        } else if (status == CANCELLED) {
//            word = "Cancelled";
//        } else {
//            word = "Error";
//        }
        return word;
    }
    
    public Integer getNextStatus() {
        if (status != ERROR && status < RECEIVED) {
            return status + 1;
        } else {
            return ERROR;
        }
    }

    public Date getObtainedDate() {
        return obtainedDate;
    }
    
    public boolean isCancelled() {
        return (status == CANCELLED);
    }
    
    public boolean isError() {
        return (status == ERROR);
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setObtainedDate(Date obtainedDate) {
        this.obtainedDate = obtainedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "application.entity.Status[ id=" + id + " ]";
    }
    
}
