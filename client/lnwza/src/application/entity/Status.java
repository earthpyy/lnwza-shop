package application.entity;

import application.MyDate;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author SE-lnwza
 */
@Entity
public class Status {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private OrderStatus status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date obtainedDate;
    
    @ManyToOne
    private Order order;
    
    public Status(Order order, OrderStatus status, Date obtainedDate) {
        this.order = order;
        this.status = status;
        this.obtainedDate = obtainedDate;
    }
    
    public Status(Order order, OrderStatus status) {
        this(order, status, new Date());
    }
        
    public Status(Order order) {
        this(order, OrderStatus.PREPARING);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
//    public Order getOrder() {
//        return order;
//    }

    public OrderStatus getStatus() {
        return status;
    }
    
    public String getStatusName() {
        return status.getName();
    }

    public Date getDate() {
        return obtainedDate;
    }
    
    public String getObtainedDate() {
        return MyDate.getFullDate(obtainedDate);
    }
    
    public String getObtainedTime() {
        return MyDate.getTime(obtainedDate);
    }
    
    public boolean isCancelled() {
        return (status == OrderStatus.CANCELLED);
    }
    
    public boolean isError() {
        return (status == OrderStatus.ERROR);
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    public void setStatus(OrderStatus status) {
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
