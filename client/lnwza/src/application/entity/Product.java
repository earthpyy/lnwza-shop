package application.entity;

import application.ImageConverter;
import javafx.scene.image.ImageView;
import javax.persistence.*;

/**
 *
 * @author SE-lnwza
 */
@Entity
public class Product {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private String name;
    private String description;
    private String photo;
    @ManyToOne
    private ProductType type;
    private ProductDetail[] detail;
    private String size;
    private Double price;

    public Product(String productId, String name, String description, String photo, ProductType type, ProductDetail[] detail, String size, Double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.photo = ImageConverter.toByte(photo);
        this.type = type;
        this.detail = detail;
        this.size = size;
        this.price = price;
    }
    
    public Product(String productId, String name, String description, String photo, ProductType type, String colorName, String color, Integer quantity, String size, Double price) {
        this(productId, name, description, photo, type, new ProductDetail[]{new ProductDetail(colorName, color, quantity)}, size, price);
    }
    
    public Product(String productId, String name, String description, String photo, ProductType type, String colorName, String color, String size, Double price) {
        this(productId, name, description, photo, type, new ProductDetail[]{new ProductDetail(colorName, color, 0)}, size, price);
    }

    public Long getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ImageView getPhoto() {
        ImageView img = new ImageView(ImageConverter.toImage(photo));
        img.setPreserveRatio(true);
        img.setFitWidth(283);
        return img;
    }

    public String getType() {
        return type.getName();
    }

    public ProductDetail[] getDetail() {
        return detail;
    }
    
    public String getColorName(int index) {
        return detail[index].getColorName();
    }

    public String getColor(int index) {
        return detail[index].getColor();
    }

    public Integer getQuantity(int index) {
        return detail[index].getQuantity();
    }

    public String getSize() {
        return size;
    }

    public Double getPrice() {
        return price;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(String photo) {
        this.photo = ImageConverter.toByte(photo);
    }

    public void setType(String type) {
        this.type.setName(type);
    }

    public void setDetail(ProductDetail[] detail) {
        this.detail = detail;
    }
    
    public void setColorName(int index, String colorName) {
        this.detail[index].setColorName(colorName);
    }

    public void setColor(int index, String color) {
        this.detail[index].setColor(color);
    }

    public void setQuantity(int index, Integer quantity) {
        this.detail[index].setQuantity(quantity);
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "application.entity.Product[ id=" + id + " ]";
    }
    
}
