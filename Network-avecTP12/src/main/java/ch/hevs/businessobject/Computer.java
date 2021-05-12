package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Computer extends Device {
	
	@ManyToMany
	private Set<User> computerOwners;

    public Computer() {super();}
	
	public Computer(String name) {

        super(name);
        this.computerOwners = new HashSet<User>();
       
    }

  
    @Override
    public void addOwner(User user) {
        computerOwners.add(user);
    }

}
