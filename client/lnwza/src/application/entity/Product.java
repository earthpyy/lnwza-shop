package application.entity;

import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.persistence.*;

import application.ImageConverter;
import java.util.ArrayList;

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
    public ProductType type;
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "product")
    private List<ProductDetail> detail;
    private String size;
    private Double price;
    

    public Product(String productId, String name, String description, String photo, ProductType type, String size, Double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.photo = ImageConverter.toByte(photo);
        this.type = type;
        this.size = size;
        this.price = price;
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

    public Image getPhoto() {
        return ImageConverter.toImage(photo);
    }
    
    public ImageView getPhotoViewByWidth(double width) {
        ImageView img = getPhotoView();
        img.setFitWidth(width);
        return img;
    }
    
    public ImageView getPhotoViewByHeight(double height) {
        ImageView img = getPhotoView();
        img.setFitHeight(height);
        return img;
    }
    
    public ImageView getPhotoView() {
        ImageView img = new ImageView(ImageConverter.toImage(photo));
        img.setPreserveRatio(true);
        return img;
    }

    public String getType() {
        return type.getName();
    }

    public ArrayList<ProductDetail> getDetail() {
        return (ArrayList) detail;
    }
    
    public String getColorName(int index) {
        return detail.get(index).getColorName();
    }

    public String getColor(int index) {
        return detail.get(index).getColor();
    }

    public Integer getQuantity(int index) {
        return detail.get(index).getQuantity();
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

    public void setDetail(ArrayList<ProductDetail> detail) {
        this.detail = detail;
    }
    
    public void setColorName(int index, String colorName) {
        this.detail.get(index).setColorName(colorName);
    }

    public void setColor(int index, String color) {
        this.detail.get(index).setColor(color);
    }

    public void setQuantity(int index, Integer quantity) {
        this.detail.get(index).setQuantity(quantity);
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
