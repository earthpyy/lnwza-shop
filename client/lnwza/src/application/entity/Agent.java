package application.entity;

import javax.persistence.*;

/**
 *
 * @author SE-lnwza
 */
@Entity
public class Agent extends User {

    protected String email;
    protected String address;
    protected String tel;

    public Agent(String firstName, String lastName, String username, String password, String email, String address, String tel) {
        super(firstName, lastName, username, password);
        this.email = email;
        this.address = address;
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
