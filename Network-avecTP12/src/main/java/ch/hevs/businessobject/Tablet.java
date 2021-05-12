package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Tablet extends Device {

    private User owner; 

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    public Tablet() {}
    
    public Tablet(String name) {
        super(name);
        this.owner = new User();
    }

    @Override
    public void addOwner(User user) {
        owner = user;
    }
}
