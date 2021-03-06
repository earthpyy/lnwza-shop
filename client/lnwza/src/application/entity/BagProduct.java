package application.entity;

import javafx.scene.image.ImageView;
import javax.persistence.*;

/**
 *
 * @author SE-lnwza
 */
@Entity
public class BagProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ProductDetail product;
    private Double price;
    private Integer quantity;
    private Double total;
    
    @ManyToOne
    private Order order;
    
    public BagProduct(Order order, ProductDetail product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.price = product.getProduct().getPrice();
        this.quantity = quantity;
        this.total = price * quantity;
    }
    
    public BagProduct(Order order, ProductDetail product) {
        this(order, product, 1);
    }
    
    public BagProduct(ProductDetail product, Integer quantity) {
        this(null, product, quantity);
    }
    
    public BagProduct(ProductDetail product) {
        this(null, product, 1);
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

    public ProductDetail getDetail() {
        return product;
    }
    
    public String getProductName() {
        return product.getProduct().getName();
    }
    
    public ImageView getProductPhoto() {
        return product.getProduct().getPhotoViewByWidth(100);
    }
    
    public Double getProductPrice() {
        return product.getProduct().getPrice();
    }
    
    public String getColorName() {
        return product.getColorName();
    }

    public Double getPrice() {
        return price;
    }
    
    public Double getTotalPrice() {
        return price * quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotal() {
        return total;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(ProductDetail product) {
        this.product = product;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        if (!(object instanceof BagProduct)) {
            return false;
        }
        BagProduct other = (BagProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "application.entity.BagProduct[ id=" + id + " ]";
    }
    
}
