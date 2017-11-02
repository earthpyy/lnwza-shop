package application.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import application.Constant;

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
    @ManyToMany
    private List<ProductDetail> products;
    private List<Integer> quantity;
    private Double amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    private int status;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="order")
    private Transaction transaction;

    public Order(Agent agent, List<ProductDetail> products, List<Integer> quantity, Double amount, Date orderDate, int status) {
        this.agent = agent;
        this.products = products;
        this.quantity = quantity;
        this.amount = amount;
        this.orderDate = orderDate;
        this.status = status;
        this.transaction = new Transaction(null, Constant.TRAN_ORDER, null, this, amount, new Date());
    }
    
    public Order(Agent agent) {
        this(agent, new ArrayList<>(), new ArrayList<>(), new Double(0), new Date(), Constant.STATUS_NOTPAY);
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

    public ArrayList<ProductDetail> getProducts() {
        return (ArrayList) products;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getStatus() {
        return status;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void setProducts(List<ProductDetail> products) {
        this.products = products;
    }
    
    public void addProduct(ProductDetail products, Integer quantity) {
        this.products.add(products);
        this.quantity.add(quantity);
        amount += products.getProduct().getPrice() * quantity;
        transaction.setAmount(amount);
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
