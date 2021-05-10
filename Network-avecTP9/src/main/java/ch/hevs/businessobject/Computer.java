package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Computer extends Device {

	@ManyToMany
    private Set<User> owners;

    public Computer() {super();}
	
	public Computer(String name, OperationalSystem os) {

        super(name, os);
        owners = new HashSet<User>();
    }

    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    @Override
    public void addOwner(User user) {
        owners.add(user);
    }

}
