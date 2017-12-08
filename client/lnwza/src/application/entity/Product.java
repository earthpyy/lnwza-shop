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
    private List<ProductDetail> details;
    private Double tall;
    private Double wide;
    private Double deep;
    private Double price;
    private Boolean recommended;
    

    public Product(String productId, String name, String description, String photo, ProductType type, Double tall, Double wide, Double deep, Double price, Boolean recommended) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.photo = ImageConverter.toByte(photo);
        this.type = type;
        this.tall = tall;
        this.wide = wide;
        this.deep = deep;
        this.price = price;
        this.recommended = recommended;
    }
    
    public Product(String productId, String name, String description, String photo, ProductType type, Double tall, Double wide, Double deep, Double price) {
        this(productId, name, description, photo, type, tall, wide, deep, price, false);
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
    
    public ImageView getPhotoStockView() {
        return getPhotoViewByWidth(140);
    }
    
    public ProductType getType() {
        return type;
    }

    public String getTypeName() {
        return type.getName();
    }

    public ArrayList<ProductDetail> getDetail() {
        return (ArrayList) details;
    }
    
    public ArrayList<ProductDetail> getLeftDetail() {
        ArrayList<ProductDetail> result = new ArrayList<>();
        for (ProductDetail detail : details) {
            if (detail.getQuantity() > 0) {
                result.add(detail);
            }
        }
        return result;
    }
    
    public String getAllColorAsString() {
        String result = "";
        for (ProductDetail detail : details) {
            if (!result.isEmpty()) {
                result += ", ";
            }
            result += detail.getColorName();
        }
        return result;
    }
    
    public String getColorName(int index) {
        return details.get(index).getColorName();
    }

    public Integer getQuantity(int index) {
        return details.get(index).getQuantity();
    }

    public Double getTall() {
        return tall;
    }

    public Double getWide() {
        return wide;
    }

    public Double getDeep() {
        return deep;
    }
    
    public String getSize() {
        return tall + " x " + wide + (deep == 0 ? "" : " x " + deep) + " cm";
    }

    public Double getPrice() {
        return price;
    }

    public Boolean isRecommended() {
        return recommended;
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
        this.details = detail;
    }

    public void setQuantity(int index, Integer quantity) {
        this.details.get(index).setQuantity(quantity);
    }

    public void setTall(Double tall) {
        this.tall = tall;
    }

    public void setWide(Double wide) {
        this.wide = wide;
    }

    public void setDeep(Double deep) {
        this.deep = deep;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
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
