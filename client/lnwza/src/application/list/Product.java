package application.list;

import javafx.beans.property.*;

/**
 *
 * @author SE-lnwza
 */
// TODO: change this list to entity of objectdb
public class Product {
    
    private final SimpleIntegerProperty productId;
    private final SimpleStringProperty name;
    private final SimpleStringProperty detail;
    private final SimpleStringProperty type;
    private final SimpleIntegerProperty quanity;
    private final SimpleStringProperty size;
    private final Double price;
    
    //private final *ColorObject* color;
    //private final *PhotoObject* photo;
    
    
    public Product(Integer productId, String name, String detail, String type, Integer quanity, String size, Double price) {
        this.productId = new SimpleIntegerProperty(productId);
        this.name = new SimpleStringProperty(name);
        this.detail = new SimpleStringProperty(detail);
        this.type = new SimpleStringProperty(type);
        this.quanity = new SimpleIntegerProperty(quanity);
        this.size = new SimpleStringProperty(size);
        this.price = price;
                
        //this.color = color;
        //this.photo = photo;
    }

    public int getProductId() {
        return productId.get();
    }
    
    public String getName() {
        return name.get();
    }
    
    public String getDetail() {
        return detail.get();
    }
    
    public String getType() {
        return type.get();
    }
    
    public int getQuanity() {
        return quanity.get();
    }
    
    public String getSize() {
        return size.get();
    }
    
    public Double getPrice() {
        return price;
    }
    
    //public *PhotoObject* getPhoto()
    //public *ColorObject* getColor()
}
