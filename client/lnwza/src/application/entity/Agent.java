package application.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author SE-lnwza
 */
@Entity
public class Agent {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String tel;
    
//    @OneToMany(cascade=CascadeType.ALL, mappedBy = "agent")
//    private List<Order> order;

    public Agent(String firstName, String lastName, String address, String tel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.tel = tel;
//        this.order = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getName() {
        return firstName + " " + lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

//    public ArrayList<Order> getOrder() {
//        return (ArrayList) order;
//    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

//    public void setOrder(List<Order> order) {
//        this.order = order;
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
        if (!(object instanceof Agent)) {
            return false;
        }
        Agent other = (Agent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "application.entity.Agent[ id=" + id + " ]";
    }
    
}
