package ch.hevs.businessobject;

import javax.persistence.Entity;



@Entity
public class MobilePhone extends Device {

    private User phoneOwner;  

    public User getOwner() {
        return phoneOwner;
    }

    public void setOwner(User owner) {
        this.phoneOwner = owner;
    }
    
    public MobilePhone() {}
    
    public MobilePhone(String name) {

        super(name);
        this.phoneOwner= new User();
    }
    
    @Override
    public void addOwner(User user) {
    	phoneOwner = user;
    }
}
