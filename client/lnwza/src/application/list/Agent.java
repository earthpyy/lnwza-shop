package application.list;

import javafx.beans.property.*;

/**
 *
 * @author SE-lnwza
 */
public class Agent {
    
    private final SimpleIntegerProperty agentId;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty tel;
    
    public Agent(int agentId, String firstName, String lastName, String address, String tel) {
        this.agentId = new SimpleIntegerProperty(agentId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.address = new SimpleStringProperty(address);
        this.tel = new SimpleStringProperty(tel);
    }

    public int getAgentId() {
        return agentId.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getTel() {
        return tel.get();
    }
    
}
