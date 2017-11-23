package application.entity;

import application.MyDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author SE-lnwza
 */
@Entity
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Agent agent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<BagProduct> products;
    private Double amount;
    private String trackNo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Status> status;
    
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
//    private Transaction transaction;

    public Order(Agent agent, List<BagProduct> products, Double amount, Date orderDate) {
        this.agent = agent;
        this.products = products;
        this.amount = amount;
        this.trackNo = null;
        this.orderDate = orderDate;
        this.status = new ArrayList<>(Arrays.asList(new Status(this)));
        // TODO: add transaction when order is completed.
//        this.transaction = new Transaction(null, TransactionType.ORDER, null, this, amount, orderDate);
    }
    
    public Order(Agent agent, List<BagProduct> products, Double amount) {
        this(agent, products, amount, new Date());
    }
    
    public Order(Agent agent) {
        this(agent, new ArrayList<>(), new Double(0));
    }
    
//    public Double calculateAmount() {
//        double sum = 0;
//        for (int i = 0; i < products.size(); i++) {
//            sum += products.get(i).getProduct().getPrice() * quantity.get(i);
//            System.out.println(sum);
//        }
//        return sum;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }
    
    public String getAgentName() {
        return agent.getName();
    }

    public ArrayList<BagProduct> getProducts() {
        return (ArrayList) products;
    }

    public Date getDate() {
        return orderDate;
    }
    
    public String getOrderDate() {
        return MyDate.getFullDate(orderDate);
    }
    
    public String getOrderTime() {
        return MyDate.getTime(orderDate);
    }

    public ArrayList<Status> getStatus() {
        return (ArrayList) status;
    }
    
    public Status getLastStatus() {
        return status.get(status.size() - 1);
    }
    
    public String getLastStatusName() {
        return getLastStatus().getStatusName();
    }

    public Double getAmount() {
        return amount;
    }

    public String getTrackNo() {
        return trackNo;
    }

//    public Transaction getTransaction() {
//        return transaction;
//    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

//    public void setProducts(List<BagProduct> products) {
//        this.products = products;
//    }
    
    public void addProduct(BagProduct products) {
        products.setOrder(this);
        this.products.add(products);
        amount += products.getTotalPrice();
//        transaction.setAmount(amount);
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }
    
    public void addStatus(Status status) {
        this.status.add(status);
    }
    
    public void addStatus(OrderStatus status) {
        this.status.add(new Status(this, status));
    }
    
    public void addNextStatus() {
        addStatus(getLastStatus().getStatus().getNext());
    }
    
    public void addCancelledStatus() {
        if (getLastStatus().getStatus() != OrderStatus.ERROR && getLastStatus().getStatus() != OrderStatus.CANCELLED)
            addStatus(OrderStatus.CANCELLED);
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }

//    public void setTransaction(Transaction transaction) {
//        this.transaction = transaction;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "application.entity.Order[ id=" + id + " ]";
    }
    
}
