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
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated
    private TransactionType type;
    @ManyToOne
    private ProductDetail product;
    @OneToOne
    private Order order;
    private Double amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tranDate;
    
    // temp field (will use when load data)
    private Double total;
    
    public Transaction(String description, TransactionType type, ProductDetail product, Order order, Double amount, Date tranDate) {
        
        this.description = description;
        this.type = type;
        this.product = product;
        this.order = order;
        this.amount = amount;
        this.tranDate = tranDate;
        this.total = 0.0;
    }
    
    public Transaction(String description, TransactionType type, ProductDetail product, Order order, Double amount) {
        this(description, type, product, order, amount, new Date());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getType() {
        return type;
    }

    public ProductDetail getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getTranDate() {
        return tranDate;
    }
    
    public Double getTotal() {
        return total;
    }
    
    public String getDate() {
        return MyDate.getFullDate(tranDate);
    }
    
    public String getTime() {
        return MyDate.getTime(tranDate);
    }
    
    public Calendar getCalendar() {
        return MyDate.newCalendar(tranDate);
    }
    
    public Double getIncome() {
        Double amnt = null;
        if (getAmount() > 0)
            amnt = getAmount();
        return amnt;
    }
    
    public Double getOutcome() {
        Double amnt = null;
        if (getAmount() < 0)
            amnt = Math.abs(getAmount());
        return amnt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setProduct(ProductDetail product) {
        this.product = product;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTranDate(Date date) {
        this.tranDate = date;
    }
    
    public void setTotal(Double total) {
        this.total = total;
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
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "application.entity.TransactionDetail[ id=" + id + " ]";
    }
    
}
