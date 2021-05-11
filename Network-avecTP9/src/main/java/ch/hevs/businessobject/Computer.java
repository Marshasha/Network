package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Computer extends Device  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

    public long getId() {
		return id;
	}

    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OperationalSystem osComputer;

    public Computer() {super();}
	
	public Computer(String name, OperationalSystem os) {

        super(name, os);
//        owners = new HashSet<User>();
    }

	/*	@ManyToMany
    private Set<User> owners;

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
*/
}
