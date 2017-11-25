package application.entity;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author SE-lnwza
 */
@Entity
public class Owner extends User {
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoggedIn;

    public Owner(String firstName, String lastName, String username, String password) {
        super(firstName, lastName, username, password);
        this.lastLoggedIn = null;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }
    
    @Override
    public boolean isOwner() {
        return true;
    }
    
    @Override
    public String getRole() {
        return "Owner";
    }

    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
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
        if (!(object instanceof Owner)) {
            return false;
        }
        Owner other = (Owner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "application.entity.Owner[ id=" + id + " ]";
    }
    
}
