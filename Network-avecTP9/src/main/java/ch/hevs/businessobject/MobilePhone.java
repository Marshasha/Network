package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class MobilePhone extends Device {

	@OneToMany
    private User owner;

    public MobilePhone(String name, OperationalSystem os) {

        super(name, os);
        this.owner= new User();
    }

    @Override
    public void addOwner(User user) {
        owner = user;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
