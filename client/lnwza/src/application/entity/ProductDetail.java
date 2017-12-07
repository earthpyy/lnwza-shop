package application.entity;

import javafx.scene.paint.Color;
import javax.persistence.*;

/**
 *
 * @author EARTHPYY
 */
@Entity
public class ProductDetail {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String colorName;
    private String colorCode;
    private Integer quantity;
    
    @ManyToOne
    private Product product;

    public ProductDetail(Product product, String colorName, String colorCode, Integer quantity) {
        this.product = product;
        this.colorName = colorName;
        this.colorCode = colorCode.replace("#", "");
        this.quantity = quantity;
    }
    
    public ProductDetail(Product product, String colorName, String colorCode) {
        this(product, colorName, colorCode, 0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public String getColorName() {
        return colorName;
    }

    public Color getColor() {
        return new Color(Integer.valueOf(colorCode.substring(0, 2), 16) / 255.0, Integer.valueOf(colorCode.substring(2, 4), 16) / 255.0, Integer.valueOf(colorCode.substring(4, 6), 16) / 255.0, 1);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public void decreaseQuantity(Integer quantity) {
        this.quantity -= quantity;
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
        if (!(object instanceof ProductDetail)) {
            return false;
        }
        ProductDetail other = (ProductDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "application.entity.ProductColor[ id=" + id + " ]";
    }
    
}
